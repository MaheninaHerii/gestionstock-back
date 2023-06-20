package toky.gestiondestock.dao.entreprise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.entreprise.Entreprise;

import java.util.Optional;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    Optional<Entreprise> findEntrepriseByCode(String code);
}
