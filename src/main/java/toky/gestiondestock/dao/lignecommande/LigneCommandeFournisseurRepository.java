package toky.gestiondestock.dao.lignecommande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.ligne.LigneCommandeFournisseur;

@Repository
public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Long> {
}
