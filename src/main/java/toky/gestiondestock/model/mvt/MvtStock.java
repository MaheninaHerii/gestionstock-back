package toky.gestiondestock.model.mvt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import toky.gestiondestock.model.AbstractEntity;
import toky.gestiondestock.model.article.Article;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvm_stock")
public class MvtStock extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    @Column(name = "date_mvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "type_mvt")
    private TypeMvmStock typeMvmStock;
}
