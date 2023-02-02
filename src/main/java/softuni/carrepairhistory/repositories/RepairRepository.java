package softuni.carrepairhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carrepairhistory.models.entities.Repair;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
}
