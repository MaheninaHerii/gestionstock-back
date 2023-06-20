package toky.gestiondestock.dao.lignecommande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.ligne.LigneVente;

@Repository
public interface LigneVenteRepository extends JpaRepository<LigneVente, Long> {
}
