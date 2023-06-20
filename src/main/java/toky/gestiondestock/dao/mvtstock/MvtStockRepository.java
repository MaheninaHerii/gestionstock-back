package toky.gestiondestock.dao.mvtstock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toky.gestiondestock.model.mvt.MvtStock;

@Repository
public interface MvtStockRepository extends JpaRepository<MvtStock, Long> {
}
