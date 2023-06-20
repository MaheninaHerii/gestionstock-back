package toky.gestiondestock.dto.fournisseur;


import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.adresse.AdresseDto;
import toky.gestiondestock.dto.commande.CommandeFournisseurDto;
import toky.gestiondestock.model.fournisseur.Fournisseur;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FournisseurDto {
    
    private long id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String email;

    private String phone;

    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (fournisseur == null) {
            //TODO throw  error exception
            return null;
        }

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .photo(fournisseur.getPhoto())
                .email(fournisseur.getEmail())
                .phone(fournisseur.getPhone())
                .commandeFournisseurs(
                        fournisseur.getCommandeFournisseurs() != null ?
                                fournisseur.getCommandeFournisseurs().stream()
                                        .map(CommandeFournisseurDto::fromEntity)
                                        .collect(Collectors.toList()):null).build();
    }

    public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
        if (fournisseurDto == null) {
            //TODO throw error exception
            return null;
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresse()));
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setPhone(fournisseurDto.getPhone());
        fournisseur.setCommandeFournisseurs(
                fournisseurDto.getCommandeFournisseurs() != null ?
                        fournisseurDto.getCommandeFournisseurs().stream()
                                .map(CommandeFournisseurDto::toEntity)
                                .collect(Collectors.toList()) : null);

        return fournisseur;
    }
}
