package toky.gestiondestock.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import toky.gestiondestock.dto.client.ClientDto;
import toky.gestiondestock.services.client.ClientService;

import java.util.List;

@RestController
public class ClientController implements ClientApi {
    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        return service.save(clientDto);
    }

    @Override
    public ClientDto findById(Long id) {
        return service.findById(id);
    }

    @Override
    public ClientDto findClientByCode(String code) {
        return service.findClientByCode(code);
    }

    @Override
    public List<ClientDto> findAll() {
        return service.findAll();
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
