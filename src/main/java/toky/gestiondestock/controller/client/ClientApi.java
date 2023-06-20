package toky.gestiondestock.controller.client;

import org.springframework.web.bind.annotation.RequestBody;
import toky.gestiondestock.dto.client.ClientDto;

import java.util.List;

import static toky.gestiondestock.utils.Constants.APP_ROOT;


public interface ClientApi {
    ClientDto save(@RequestBody ClientDto clientDto);
    ClientDto findById(Long id);
    ClientDto findClientByCode(String code);
    List<ClientDto> findAll();
    void delete(Long id);
}
