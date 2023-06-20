package toky.gestiondestock.dao.lignecommande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.ligne.LigneCommandeClient;

@Repository
public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Long> {
}
