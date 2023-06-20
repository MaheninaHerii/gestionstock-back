package toky.gestiondestock.dto.entreprise;

import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.article.ArticleDto;
import toky.gestiondestock.dto.utilisateur.UserDto;
import toky.gestiondestock.model.entreprise.Entreprise;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class EntrepriseDto {

    private long id;

    private String nom;

    private String description;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTelephone;

    private String siteweb;

    private List<ArticleDto> articleList;

    private List<UserDto> users;

    public static EntrepriseDto fromEntity(Entreprise entreprise) {
        if (entreprise == null) {
            //TODO throw  error exception
            return null;
        }

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .numTelephone(entreprise.getNumTelephone())
                .articleList(
                        entreprise.getArticleList() != null ?
                                entreprise.getArticleList().stream()
                                        .map(ArticleDto::fromEntity)
                                        .collect(Collectors.toList()):null).build();
    }

    public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
        if (entrepriseDto == null) {
            //TODO throw error exception
            return null;
        }

        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setNumTelephone(entrepriseDto.getNumTelephone());
        entreprise.setArticleList(
                entrepriseDto.getArticleList() != null ?
                        entrepriseDto.getArticleList().stream()
                                .map(ArticleDto::toEntity)
                                .collect(Collectors.toList()) : null);

        return entreprise;
    }
}
