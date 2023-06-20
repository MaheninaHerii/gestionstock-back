package toky.gestiondestock.services.client;



import toky.gestiondestock.dto.client.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto clientDto);
    ClientDto findById(Long id);
    ClientDto findClientByCode(String code);
    List<ClientDto> findAll();
    void delete(Long id);
}
