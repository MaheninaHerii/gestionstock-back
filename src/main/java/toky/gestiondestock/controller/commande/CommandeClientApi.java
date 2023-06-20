package toky.gestiondestock.controller.commande;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toky.gestiondestock.dto.commande.CommandeClientDto;

import java.util.List;

import static toky.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeClientApi {

    @PostMapping(APP_ROOT+"/creation/commande/client")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @GetMapping(APP_ROOT+"/commandeclients/{Id}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable Long Id);

    @GetMapping(APP_ROOT+"/commandeclients/{code}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable String code);

    @GetMapping(APP_ROOT+"/commandeclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(APP_ROOT+"/commandeclient/delete/{Id}")
    ResponseEntity delete(@PathVariable Long Id);
}
