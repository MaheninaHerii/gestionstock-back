package toky.gestiondestock.dto.lignecommande;

import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.article.ArticleDto;
import toky.gestiondestock.dto.commande.CommandeFournisseurDto;
import toky.gestiondestock.model.ligne.LigneCommandeFournisseur;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto {

    private long id;

    private ArticleDto article;

    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (ligneCommandeFournisseur == null) {
            //TODO throw  error exception
            return null;
        }

        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .commandeFournisseur(CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire()).build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        if (ligneCommandeFournisseurDto == null) {
            //TODO throw error exception
            return null;
        }

        LigneCommandeFournisseur ligne = new LigneCommandeFournisseur();
        ligne.setId(ligneCommandeFournisseurDto.getId());
        ligne.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()));
        ligne.setCommandeFournisseur(CommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto.getCommandeFournisseur()));
        ligne.setQuantite(ligneCommandeFournisseurDto.getQuantite());
        ligne.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());

        return ligne;
    }
}
