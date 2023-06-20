package toky.gestiondestock.dto.utilisateur;

import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.model.utilisateur.Role;

@Data
@Builder
public class RoleDto {

    private long id;

    private String nom;

    private UserDto user;

    public static RoleDto fromEntity(Role role) {
        if (role == null) {
            //TODO throw  error exception
            return null;
        }

        return RoleDto.builder()
                .id(role.getId())
                .nom(role.getNom())
                .user(UserDto.fromEntity(role.getUser())).build();
    }

    public static Role toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            //TODO throw error exception
            return null;
        }

        Role role = new Role();
        role.setId(roleDto.getId());
        role.setNom(roleDto.getNom());
        role.setUser(UserDto.toEntity(roleDto.getUser()));

        return role;
    }
}
