package toky.gestiondestock.model.entreprise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import toky.gestiondestock.model.AbstractEntity;
import toky.gestiondestock.model.article.Article;
import toky.gestiondestock.model.utilisateur.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "entreprise")
public class Entreprise extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "code_fiscal")
    private String codeFiscal;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;

    @Column(name = "num_telephone")
    private String numTelephone;

    @Column(name = "siteweb")
    private String siteweb;

    @OneToMany(mappedBy = "entreprise")
    private List<Article> articleList;

    @OneToMany(mappedBy = "entreprise")
    private List<User> users;
}
