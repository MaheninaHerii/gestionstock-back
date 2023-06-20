package toky.gestiondestock.services.commande;


import toky.gestiondestock.dto.commande.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournisseurService {
    CommandeFournisseurDto save(CommandeFournisseurDto dto);

    CommandeFournisseurDto findById(Long Id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findAll();

    void delete(Long Id);
}
