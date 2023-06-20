package toky.gestiondestock.dao.categorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.categorie.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findCategoryByCode(String code);
}
