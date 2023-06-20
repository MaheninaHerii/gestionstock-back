package toky.gestiondestock.controller.vente;


import toky.gestiondestock.dto.vente.VenteDto;

import java.util.List;

public interface VenteApi {
    VenteDto save(VenteDto venteDto);
    VenteDto findById(Long id);
    VenteDto findVenteByCode(String code);
    List<VenteDto> findAll();
    void delete(Long id);
}
