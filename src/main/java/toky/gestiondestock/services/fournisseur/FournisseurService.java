package toky.gestiondestock.services.fournisseur;



import toky.gestiondestock.dto.fournisseur.FournisseurDto;

import java.util.List;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto fournisseurDto);
    FournisseurDto findById(Long id);
    FournisseurDto findFournisseurByCode(String code);
    List<FournisseurDto> findAll();
    void delete(Long id);
}
