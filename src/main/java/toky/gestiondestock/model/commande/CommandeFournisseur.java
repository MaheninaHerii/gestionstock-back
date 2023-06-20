package toky.gestiondestock.model.commande;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import toky.gestiondestock.model.AbstractEntity;
import toky.gestiondestock.model.fournisseur.Fournisseur;
import toky.gestiondestock.model.ligne.LigneCommandeFournisseur;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commande_fournisseur")
public class CommandeFournisseur extends AbstractEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "date_commande")
    private Instant dateCommande;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandeFournisseur")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;
}
