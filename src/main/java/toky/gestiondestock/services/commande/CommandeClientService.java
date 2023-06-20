package toky.gestiondestock.services.commande;


import toky.gestiondestock.dto.commande.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {
    CommandeClientDto save(CommandeClientDto dto);

    CommandeClientDto findById(Long Id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    void delete(Long Id);
}
