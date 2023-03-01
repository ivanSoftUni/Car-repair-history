package softuni.carrepairhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carrepairhistory.models.entities.PartsCategory;

import java.util.Optional;

@Repository
public interface PartsCategoryRepository extends JpaRepository<PartsCategory, Long> {
    Optional<PartsCategory> findByName(String name);

}
