package toky.gestiondestock.dto.lignecommande;

import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.article.ArticleDto;
import toky.gestiondestock.dto.vente.VenteDto;
import toky.gestiondestock.model.ligne.LigneVente;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private long id;

    private ArticleDto article;

    private VenteDto vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneVenteDto fromEntity(LigneVente ligneVente) {
        if (ligneVente == null) {
            //TODO throw  error exception
            return null;
        }

        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .vente(VenteDto.fromEntity(ligneVente.getVente()))
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire()).build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        if (ligneVenteDto == null) {
            //TODO throw error exception
            return null;
        }

        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticle()));
        ligneVente.setVente(VenteDto.toEntity(ligneVenteDto.getVente()));
        ligneVente.setQuantite(ligneVente.getQuantite());
        ligneVente.setPrixUnitaire(ligneVente.getPrixUnitaire());

        return ligneVente;
    }
}
