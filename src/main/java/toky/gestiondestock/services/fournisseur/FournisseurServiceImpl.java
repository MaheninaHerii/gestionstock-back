package toky.gestiondestock.services.fournisseur;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toky.gestiondestock.dao.fournisseur.FournisseurRepository;
import toky.gestiondestock.dto.fournisseur.FournisseurDto;
import toky.gestiondestock.exception.ErrorCodes;
import toky.gestiondestock.exception.InvalidException;
import toky.gestiondestock.exception.NotFoundException;
import toky.gestiondestock.model.fournisseur.Fournisseur;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService{
    private final FournisseurRepository fournisseur;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        if(fournisseurDto == null) {
            log.error("Fournisseur is not valid",fournisseurDto);
            throw new InvalidException("Le Fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID);
        }
        return FournisseurDto.fromEntity(fournisseur.save(FournisseurDto.toEntity(fournisseurDto)));
    }

    @Override
    public FournisseurDto findById(Long id) {
        if (id == null) {
            log.error("Fournisseur ID is Null");
            return null;
        }
        Optional<Fournisseur> fr = fournisseur.findById(id);
        FournisseurDto fournisseurDto = FournisseurDto.fromEntity(fr.get());
        return Optional.of(fournisseurDto).orElseThrow(()->new NotFoundException("Aucun Fournisseur avec l'ÍD = " +
                id + "n'etes trouve dans la BDD",ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public FournisseurDto findFournisseurByCode(String code) {
        if (code == null) {
            log.error("Code Fournisseur is Null");
            return null;
        }
        Optional<Fournisseur> fr = fournisseur.findFournisseurByCode(code);
        FournisseurDto fournisseurDto = FournisseurDto.fromEntity(fr.get());
        return Optional.of(fournisseurDto).orElseThrow(()->new NotFoundException("Aucun Fournisseur avec le Code = " +
                code + "n'etes trouve dans la BDD",ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return  fournisseur.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Fournisseur ID is Null");
        }
        fournisseur.deleteById(id);
    }
}
