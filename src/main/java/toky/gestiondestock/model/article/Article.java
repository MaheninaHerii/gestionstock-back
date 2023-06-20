package toky.gestiondestock.model.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import toky.gestiondestock.model.AbstractEntity;
import toky.gestiondestock.model.categorie.Category;
import toky.gestiondestock.model.entreprise.Entreprise;
import toky.gestiondestock.model.ligne.LigneCommandeClient;
import toky.gestiondestock.model.ligne.LigneCommandeFournisseur;
import toky.gestiondestock.model.ligne.LigneVente;
import toky.gestiondestock.model.mvt.MvtStock;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AbstractEntity {
    @Column(name = "code_article")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prix_unitaire_ht")
    private BigDecimal prixUnitaireHT;

    @Column(name = "taux_tva")
    private BigDecimal  tauxTva;

    @Column(name = "prix_unitaire_ttc")
    private BigDecimal prixUnitaireTTC;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Category category;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @OneToMany(mappedBy = "article")
    private List<LigneVente> ligneVentes;

    @OneToMany(mappedBy = "article")
    private List<MvtStock> mvmStockList;

    @ManyToOne
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;
}
