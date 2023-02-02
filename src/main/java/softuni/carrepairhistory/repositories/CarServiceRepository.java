package softuni.carrepairhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carrepairhistory.models.entities.CarService;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService, Long> {
}
