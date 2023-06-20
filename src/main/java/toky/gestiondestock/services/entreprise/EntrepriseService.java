package toky.gestiondestock.services.entreprise;


import toky.gestiondestock.dto.entreprise.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {
    EntrepriseDto save(EntrepriseDto entrepriseDto);
    EntrepriseDto findById(Long id);
    EntrepriseDto findEntrepriseByCode(String code);
    List<EntrepriseDto> findAll();
    void delete(Long id);
}
