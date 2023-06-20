package toky.gestiondestock.dao.commande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.commande.CommandeClient;

import java.util.Optional;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient,Long> {
    Optional<CommandeClient> findCommandeClientByCode(String code);
}
