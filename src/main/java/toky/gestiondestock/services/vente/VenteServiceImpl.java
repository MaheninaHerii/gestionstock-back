package toky.gestiondestock.services.vente;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toky.gestiondestock.dao.article.ArticleRepository;
import toky.gestiondestock.dao.lignecommande.LigneVenteRepository;
import toky.gestiondestock.dao.vente.VenteRepository;
import toky.gestiondestock.dto.lignecommande.LigneVenteDto;
import toky.gestiondestock.dto.vente.VenteDto;
import toky.gestiondestock.exception.ErrorCodes;
import toky.gestiondestock.exception.InvalidException;
import toky.gestiondestock.exception.NotFoundException;
import toky.gestiondestock.model.article.Article;
import toky.gestiondestock.model.ligne.LigneVente;
import toky.gestiondestock.model.vente.Vente;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService{
    private final VenteRepository venteRepository;
    private final LigneVenteRepository ligne;
    private final ArticleRepository article;

    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository, LigneVenteRepository ligne, ArticleRepository article) {
        this.venteRepository = venteRepository;
        this.ligne = ligne;
        this.article = article;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        if (venteDto == null) {
            log.warn("");
            throw new InvalidException("Vente nést pas valind", ErrorCodes.VENTE_IS_NOT_VALID);
        }
        if (venteDto.getLigneVente() != null) {
            venteDto.getLigneVente().forEach(cl ->{
                if (cl.getArticle() != null) {
                    Optional<Article> atr = article.findById(cl.getArticle().getId());
                    if (atr.isEmpty()) {
                        log.warn("Article is not found");
                        throw new NotFoundException("Article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND);
                    }else {
                        log.warn("Impossible d'enregistrer un article null");
                    }
                }
            });
        }
        Vente vt = venteRepository.save(VenteDto.toEntity(venteDto));
        if (venteDto.getLigneVente() != null) {
            venteDto.getLigneVente().forEach(lgnVt->{
                LigneVente ligneVt = LigneVenteDto.toEntity(lgnVt);
                ligneVt.setVente(vt);
                ligne.save(ligneVt);
            });
        }
        return VenteDto.fromEntity(vt);
    }

    @Override
    public VenteDto findById(Long id) {
        if (id == null) {
            log.error("Vente ID is Null");
            return null;
        }
        return venteRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(() ->
                        new NotFoundException("Aucune vente n'a ete trouve avec l'ID "
                                + id,ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VenteDto findVenteByCode(String code) {
        if (code == null) {
            log.error("Vente ID is Null");
            return null;
        }
        return venteRepository.findVenteByCode(code)
                .map(VenteDto::fromEntity)
                .orElseThrow(() ->
                        new NotFoundException("Aucune vente n'a ete trouve avec le code "
                                + code,ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Vente "+id+" is Null");
        }
        venteRepository.deleteById(id);
    }
}
