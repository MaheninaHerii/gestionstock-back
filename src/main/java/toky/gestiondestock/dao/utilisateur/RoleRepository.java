package toky.gestiondestock.dao.utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.utilisateur.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
