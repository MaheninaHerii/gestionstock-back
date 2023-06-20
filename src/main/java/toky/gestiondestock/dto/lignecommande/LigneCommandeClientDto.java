package toky.gestiondestock.dto.lignecommande;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.article.ArticleDto;
import toky.gestiondestock.dto.commande.CommandeClientDto;
import toky.gestiondestock.model.ligne.LigneCommandeClient;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

    private long id;

    private ArticleDto article;

    @JsonIgnore
    private CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
        if (ligneCommandeClient == null) {
            //TODO throw  error exception
            return null;
        }

        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire()).build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
        if (ligneCommandeClientDto == null) {
            //TODO throw error exception
            return null;
        }

        LigneCommandeClient ligne = new LigneCommandeClient();
        ligne.setId(ligneCommandeClientDto.getId());
        ligne.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticle()));
        ligne.setQuantite(ligneCommandeClientDto.getQuantite());
        ligne.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());

        return ligne;
    }
}
