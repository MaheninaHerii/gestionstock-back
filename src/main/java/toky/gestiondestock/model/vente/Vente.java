package toky.gestiondestock.model.vente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import toky.gestiondestock.model.AbstractEntity;
import toky.gestiondestock.model.ligne.LigneVente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vente")
public class Vente extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVentes;

    @Column(name = "date_vente")
    private Instant dateVente;

    @Column(name = "commantaires")
    private String commentaires;
}
