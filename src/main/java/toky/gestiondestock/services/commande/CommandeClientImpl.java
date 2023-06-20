package toky.gestiondestock.services.commande;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toky.gestiondestock.dao.article.ArticleRepository;
import toky.gestiondestock.dao.client.ClientRepository;
import toky.gestiondestock.dao.commande.CommandeClientRepository;
import toky.gestiondestock.dao.lignecommande.LigneCommandeClientRepository;
import toky.gestiondestock.dto.commande.CommandeClientDto;
import toky.gestiondestock.dto.lignecommande.LigneCommandeClientDto;
import toky.gestiondestock.exception.ErrorCodes;
import toky.gestiondestock.exception.InvalidException;
import toky.gestiondestock.exception.NotFoundException;
import toky.gestiondestock.model.article.Article;
import toky.gestiondestock.model.client.Client;
import toky.gestiondestock.model.commande.CommandeClient;
import toky.gestiondestock.model.ligne.LigneCommandeClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientImpl implements CommandeClientService {
    private final CommandeClientRepository commandeClientRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        if(dto == null) {
            log.error("Commande Client is not valid",dto);
            throw new InvalidException("La Commande Client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID);
        }
        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Client with Id {} was not found in the DB", dto.getClient().getId());
            throw new NotFoundException("Aucun client avec l'ID"+dto.getClient().getId()+"n'a ete trouve dans la BDD", ErrorCodes.COMMNDE_CLIENT_NOT_FOUND);
        }
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(cl ->{
                if (cl.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(cl.getArticle().getId());
                    if (article.isEmpty()) {
                        log.warn("Article is not found");
                        throw new NotFoundException("Article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND);
                    }else {
                        log.warn("Impossible d'enregistrer un article null");
                    }
                }
            });
        }
       CommandeClient commandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(lgnCmdCl->{
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(lgnCmdCl);
                ligneCommandeClient.setCommandeClient(commandeClient);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(commandeClient);
    }

    @Override
    public CommandeClientDto findById(Long Id) {
        if (Id == null) {
            log.error("Commande ID is Null");
            return null;
        }
        return commandeClientRepository.findById(Id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() ->
                        new NotFoundException("Aucune commande client n'a ete trouve avec l'ID "
                                + Id,ErrorCodes.COMMNDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (code == null) {
            log.error("Code Commande Client is Null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() ->
                        new NotFoundException("Aucune commande client n'a ete trouve avec le code "
                                + code,ErrorCodes.COMMNDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long Id) {
        if (Id == null) {
            log.error("Commande Client "+Id+" is Null");
        }
        commandeClientRepository.deleteById(Id);
    }
}
