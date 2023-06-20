package toky.gestiondestock.services.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toky.gestiondestock.dao.client.ClientRepository;
import toky.gestiondestock.dto.client.ClientDto;
import toky.gestiondestock.exception.ErrorCodes;
import toky.gestiondestock.exception.InvalidException;
import toky.gestiondestock.exception.NotFoundException;
import toky.gestiondestock.model.client.Client;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        if(clientDto == null) {
            log.error("Client is not valid",clientDto);
            throw new InvalidException("Le Client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID);
        }
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Long id) {
        if (id == null) {
            log.error("Client ID is Null");
            return null;
        }
        Optional<Client> cl = clientRepository.findById(id);
        ClientDto clientDto = ClientDto.fromEntity(cl.get());
        return Optional.of(clientDto).orElseThrow(()->new NotFoundException("Aucun Client avec l'ÍD = " +
                id + "n'etes trouve dans la BDD",ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public ClientDto findClientByCode(String code) {
        if (code == null) {
            log.error("Code Client is Null");
            return null;
        }
        Optional<Client> clt = clientRepository.findClientByCode(code);
        ClientDto clientDto = ClientDto.fromEntity(clt.get());
        return Optional.of(clientDto).orElseThrow(()->new NotFoundException("Aucun Client avec le Code = " +
                code + "n'etes trouve dans la BDD",ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return  clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Client ID is Null");
        }
        clientRepository.deleteById(id);
    }
}
