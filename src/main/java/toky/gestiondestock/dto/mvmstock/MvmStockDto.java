package toky.gestiondestock.dto.mvmstock;

import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.article.ArticleDto;
import toky.gestiondestock.model.mvt.MvtStock;
import toky.gestiondestock.model.mvt.TypeMvmStock;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvmStockDto {

    private long id;

    private ArticleDto article;

    private Instant dateMvt;

    private BigDecimal quantite;

    private TypeMvmStock typeMvmStock;

    public static MvmStockDto fromEntity(MvtStock mvmStock) {
        if (mvmStock == null) {
            //TODO throw  error exception
            return null;
        }

        return MvmStockDto.builder()
                .id(mvmStock.getId())
                .article(ArticleDto.fromEntity(mvmStock.getArticle()))
                .dateMvt(mvmStock.getDateMvt())
                .quantite(mvmStock.getQuantite())
                .typeMvmStock(mvmStock.getTypeMvmStock()).build();
    }

    public static MvtStock toEntity(MvmStockDto mvmStockDto) {
        if (mvmStockDto == null) {
            //TODO throw error exception
            return null;
        }

        MvtStock mvmStock = new MvtStock();
        mvmStock.setId(mvmStockDto.getId());
        mvmStock.setArticle(ArticleDto.toEntity(mvmStockDto.getArticle()));
        mvmStock.setDateMvt(mvmStock.getDateMvt());
        mvmStock.setQuantite(mvmStockDto.getQuantite());
        mvmStock.setTypeMvmStock(mvmStockDto.getTypeMvmStock());

        return mvmStock;
    }
}
