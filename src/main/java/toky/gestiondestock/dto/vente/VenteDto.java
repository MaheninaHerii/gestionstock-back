package toky.gestiondestock.dto.vente;


import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.lignecommande.LigneVenteDto;
import toky.gestiondestock.model.vente.Vente;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VenteDto {

    private long id;

    private String code;

    private List<LigneVenteDto> ligneVente;

    private Instant dateVente;

    private String commentaires;

    public static VenteDto fromEntity(Vente vente) {
        if (vente == null) {
            //TODO throw  error exception
            return null;
        }

        return VenteDto.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .dateVente(vente.getDateVente())
                .commentaires(vente.getCommentaires()).build();
    }

    public static Vente toEntity(VenteDto venteDto) {
        if (venteDto == null) {
            //TODO throw error exception
            return null;
        }

        Vente vente = new Vente();
        vente.setId(venteDto.getId());
        vente.setCode(venteDto.getCode());
        vente.setDateVente(venteDto.getDateVente());
        vente.setCommentaires(venteDto.getCommentaires());

        return vente;
    }
}
