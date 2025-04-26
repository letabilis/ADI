package spring.project.adi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.project.adi.model.User;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}
