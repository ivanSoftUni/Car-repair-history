package softuni.carrepairhistory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.carrepairhistory.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
