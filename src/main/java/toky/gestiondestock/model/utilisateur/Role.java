package toky.gestiondestock.model.utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import toky.gestiondestock.model.AbstractEntity;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {
    @Column(name = "nom")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
