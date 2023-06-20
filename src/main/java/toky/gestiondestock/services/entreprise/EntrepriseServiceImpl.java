package toky.gestiondestock.services.entreprise;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toky.gestiondestock.dao.entreprise.EntrepriseRepository;
import toky.gestiondestock.dto.entreprise.EntrepriseDto;
import toky.gestiondestock.exception.ErrorCodes;
import toky.gestiondestock.exception.InvalidException;
import toky.gestiondestock.exception.NotFoundException;
import toky.gestiondestock.model.entreprise.Entreprise;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService{
    private final EntrepriseRepository entreprise;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        if(entrepriseDto == null) {
            log.error("Entreprise is not valid",entrepriseDto);
            throw new InvalidException("L'Entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID);
        }
        return EntrepriseDto.fromEntity(entreprise.save(EntrepriseDto.toEntity(entrepriseDto)));
    }

    @Override
    public EntrepriseDto findById(Long id) {
        if (id == null) {
            log.error("Entreprise ID is Null");
            return null;
        }
        Optional<Entreprise> entreprise1 = entreprise.findById(id);
        EntrepriseDto entrepriseDto = EntrepriseDto.fromEntity(entreprise1.get());
        return Optional.of(entrepriseDto).orElseThrow(()->new NotFoundException("Aucun Entreprise avec l'ÍD = " +
                id + "n'etes trouve dans la BDD",ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public EntrepriseDto findEntrepriseByCode(String code) {
        if (code == null) {
            log.error("Code Entreprise is Null");
            return null;
        }
        Optional<Entreprise> etr = entreprise.findEntrepriseByCode(code);
        EntrepriseDto entrepriseDto = EntrepriseDto.fromEntity(etr.get());
        return Optional.of(entrepriseDto).orElseThrow(()->new NotFoundException("Aucun Entreprise avec le Code = " +
                code + "n'etes trouve dans la BDD",ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return  entreprise.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Entreprise ID is Null");
        }
        entreprise.deleteById(id);
    }
}
