package softuni.carrepairhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carrepairhistory.models.entities.VehiclesRepairsShop;

@Repository
public interface CarServiceRepository extends JpaRepository<VehiclesRepairsShop, Long> {
}
