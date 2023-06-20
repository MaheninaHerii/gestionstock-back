package toky.gestiondestock.dto.client;

import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.adresse.AdresseDto;
import toky.gestiondestock.dto.commande.CommandeClientDto;
import toky.gestiondestock.model.client.Client;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ClientDto {

    private long id;

    private String nom;

    private String prenom;

    private AdresseDto adresse;

    private String photo;

    private String email;

    private String phone;

    private List<CommandeClientDto> commandeClientList;

    public static ClientDto fromEntity(Client client) {
        if (client == null) {
            //TODO throw  error exception
            return null;
        }

        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .adresse(AdresseDto.fromEntity(client.getAdresse()))
                .photo(client.getPhoto())
                .email(client.getEmail())
                .phone(client.getPhone())
                .commandeClientList(
                        client.getCommandeClientList() != null ?
                                client.getCommandeClientList().stream()
                                        .map(CommandeClientDto::fromEntity)
                                        .collect(Collectors.toList()):null).build();
    }

    public static Client toEntity(ClientDto clientDto) {
        if (clientDto == null) {
            //TODO throw error exception
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAdresse(AdresseDto.toEntity(clientDto.getAdresse()));
        client.setPhoto(clientDto.getPhoto());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setCommandeClientList(
                clientDto.getCommandeClientList() != null ?
                clientDto.getCommandeClientList().stream()
                        .map(CommandeClientDto::toEntity)
                        .collect(Collectors.toList()) : null);

        return client;
    }
}
