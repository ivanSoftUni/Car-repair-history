package softuni.carrepairhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carrepairhistory.models.entities.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
