package toky.gestiondestock.controller.vente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import toky.gestiondestock.dto.vente.VenteDto;
import toky.gestiondestock.services.vente.VenteService;

import java.util.List;

@RestController
public class VenteController implements VenteApi{
    private final VenteService service;

    @Autowired
    public VenteController(VenteService service) {
        this.service = service;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        return service.save(venteDto);
    }

    @Override
    public VenteDto findById(Long id) {
        return service.findById(id);
    }

    @Override
    public VenteDto findVenteByCode(String code) {
        return findVenteByCode(code);
    }

    @Override
    public List<VenteDto> findAll() {
        return service.findAll();
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
