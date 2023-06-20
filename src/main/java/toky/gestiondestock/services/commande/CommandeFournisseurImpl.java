package toky.gestiondestock.services.commande;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toky.gestiondestock.dao.article.ArticleRepository;
import toky.gestiondestock.dao.commande.CommandeFournisseurRepository;
import toky.gestiondestock.dao.fournisseur.FournisseurRepository;
import toky.gestiondestock.dao.lignecommande.LigneCommandeFournisseurRepository;
import toky.gestiondestock.dto.commande.CommandeFournisseurDto;
import toky.gestiondestock.dto.lignecommande.LigneCommandeFournisseurDto;
import toky.gestiondestock.exception.ErrorCodes;
import toky.gestiondestock.exception.InvalidException;
import toky.gestiondestock.exception.NotFoundException;
import toky.gestiondestock.model.article.Article;
import toky.gestiondestock.model.commande.CommandeFournisseur;
import toky.gestiondestock.model.fournisseur.Fournisseur;
import toky.gestiondestock.model.ligne.LigneCommandeFournisseur;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurImpl implements CommandeFournisseurService{
    private final FournisseurRepository fournisseurRepository;
    private final ArticleRepository articleRepository;
    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public CommandeFournisseurImpl(FournisseurRepository fournisseurRepository, ArticleRepository articleRepository, CommandeFournisseurRepository commandeFournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        if(dto == null) {
            log.error("Commande Fournisseur is not valid",dto);
            throw new InvalidException("La Commande Fournisseur n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID);
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur with Id {} was not found in the DB", dto.getFournisseur().getId());
            throw new NotFoundException("Aucun fournisseur avec l'ID"+dto.getFournisseur().getId()+"n'a ete trouve dans la BDD", ErrorCodes.COMMANDE_FOUNISSEUR_NOT_FOUND);
        }
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(cl ->{
                if (cl.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(cl.getArticle().getId());
                    if (article.isEmpty()) {
                        log.warn("Article is not found");
                        throw new NotFoundException("Article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND);
                    }else {
                        log.warn("Impossible d'enregistrer un article null");
                    }
                }
            });
        }
        CommandeFournisseur commandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(lgnCmdCl->{
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(lgnCmdCl);
                ligneCommandeFournisseur.setCommandeFournisseur(commandeFournisseur);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(commandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Long Id) {
        if (Id == null) {
            log.error("Commande Founisseur ID is Null");
            return null;
        }
        return commandeFournisseurRepository.findById(Id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() ->
                        new NotFoundException("Aucune commande fournisseur n'a ete trouve avec l'ID "
                                + Id,ErrorCodes.COMMANDE_FOUNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (code == null) {
            log.error("Code Commande Forurnisseur is Null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() ->
                        new NotFoundException("Aucune commande fournisseur n'a ete trouve avec le code "
                                + code,ErrorCodes.COMMANDE_FOUNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return  commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long Id) {
        if (Id == null) {
            log.error("Commande Fournisseur "+Id+" is Null");
        }
        commandeFournisseurRepository.deleteById(Id);
    }
}
