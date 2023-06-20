package toky.gestiondestock.dto.article;

import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.categorie.CategoryDto;
import toky.gestiondestock.dto.entreprise.EntrepriseDto;
import toky.gestiondestock.dto.lignecommande.LigneCommandeClientDto;
import toky.gestiondestock.dto.lignecommande.LigneCommandeFournisseurDto;
import toky.gestiondestock.dto.lignecommande.LigneVenteDto;
import toky.gestiondestock.dto.mvmstock.MvmStockDto;
import toky.gestiondestock.model.article.Article;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class ArticleDto {

    private long id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHT;

    private BigDecimal  tauxTva;

    private BigDecimal prixUnitaireTTC;

    private String photo;

    private CategoryDto category;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    private List<LigneVenteDto> ligneVentes;

    private List<MvmStockDto> mvmStockList;

    private EntrepriseDto entreprise;

    public static ArticleDto fromEntity(Article article) {
        if (article == null) {
            //TODO throw  error exception
            return null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHT(article.getPrixUnitaireHT())
                .prixUnitaireTTC(article.getPrixUnitaireTTC())
                .tauxTva(article.getTauxTva())
                .photo(article.getPhoto())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .ligneCommandeClients(
                        article.getLigneCommandeClients() != null ?
                        article.getLigneCommandeClients().stream()
                                .map(LigneCommandeClientDto::fromEntity)
                                .collect(Collectors.toList()):null).build();
    }

    public static Article toEntity(ArticleDto articleDto) {
        if (articleDto == null) {
            //TODO throw error exception
            return null;
        }

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHT(articleDto.getPrixUnitaireHT());
        article.setPrixUnitaireTTC(articleDto.getPrixUnitaireTTC());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPhoto(articleDto.getPhoto());

        return article;
    }

}
