package toky.gestiondestock.dto.commande;


import lombok.Builder;
import lombok.Data;
import toky.gestiondestock.dto.client.ClientDto;
import toky.gestiondestock.dto.lignecommande.LigneCommandeClientDto;
import toky.gestiondestock.model.commande.CommandeClient;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private long id;

    private String code;

    private Instant dateCommande;

    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            //TODO throw  error exception
            return null;
        }

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .client(ClientDto.fromEntity(commandeClient.getClient())).build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
        if (commandeClientDto == null) {
            //TODO throw error exception
        }

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());

        return commandeClient;
    }
}
