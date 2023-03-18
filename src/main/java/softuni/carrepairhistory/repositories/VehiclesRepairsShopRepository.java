package softuni.carrepairhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;

import java.util.List;

@Repository
public interface VehiclesRepairsShopRepository extends JpaRepository<VehiclesRepairsShop, Long> {
    List<VehiclesRepairsShop> findAllByUserId(Long id);
}
