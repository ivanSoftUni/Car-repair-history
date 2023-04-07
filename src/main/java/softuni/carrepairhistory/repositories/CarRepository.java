package softuni.carrepairhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carrepairhistory.models.entities.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByRegisterNumber(String registerNumber);

    Optional<Car> findById(Long id);

    List<Car> findByUserEntityId(Long id);

}
