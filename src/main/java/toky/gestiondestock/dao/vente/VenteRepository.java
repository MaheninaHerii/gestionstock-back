package toky.gestiondestock.dao.vente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.vente.Vente;

import java.util.Optional;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
    Optional<Vente> findVenteByCode(String code);
}
