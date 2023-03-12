package softuni.carrepairhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carrepairhistory.models.entities.Part;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findByName(String name);

}
