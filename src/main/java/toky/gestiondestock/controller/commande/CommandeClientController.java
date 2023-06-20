package toky.gestiondestock.controller.commande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import toky.gestiondestock.dto.commande.CommandeClientDto;
import toky.gestiondestock.services.commande.CommandeClientService;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {
    private final CommandeClientService service;

    @Autowired
    public CommandeClientController(CommandeClientService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Long Id) {
        return ResponseEntity.ok(service.findById(Id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(service.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity delete(Long Id) {
        service.delete(Id);
        return ResponseEntity.ok().build();
    }
}
