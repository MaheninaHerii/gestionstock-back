package toky.gestiondestock.dto.utilisateur;

import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.entreprise.EntrepriseDto;
import toky.gestiondestock.model.utilisateur.User;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {

    private long id;

    private String nom;

    private String prenom;

    private Instant dateNaissance;

    private String photo;

    private String email;

    private String phone;

    private EntrepriseDto entrepriseDto;

    private List<RoleDto> roleList;

    public static UserDto fromEntity(User user) {
        if (user == null) {
            //TODO throw  error exception
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .dateNaissance(user.getDateNaissance())
                .photo(user.getPhoto())
                .email(user.getEmail())
                .phone(user.getPhone())
                .entrepriseDto(EntrepriseDto.fromEntity(user.getEntreprise()))
                .roleList(user.getRoleList() != null ?
                        user.getRoleList().stream()
                                .map(RoleDto::fromEntity)
                                .collect(Collectors.toList()) : null).build();
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null) {
            //TODO throw error exception
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setDateNaissance(userDto.getDateNaissance());
        user.setPhoto(userDto.getPhoto());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setEntreprise(EntrepriseDto.toEntity(userDto.getEntrepriseDto()));
        user.setRoleList(userDto.getRoleList() != null ?
                userDto.getRoleList().stream()
                        .map(RoleDto::toEntity)
                        .collect(Collectors.toList()) : null);
        return user;
    }


}
