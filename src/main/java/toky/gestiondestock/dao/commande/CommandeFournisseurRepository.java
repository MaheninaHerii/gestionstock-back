package toky.gestiondestock.dao.commande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.commande.CommandeFournisseur;

import java.util.Optional;

@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Long> {
    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);
}
