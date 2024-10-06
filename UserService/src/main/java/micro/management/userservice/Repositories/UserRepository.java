package micro.management.userservice.Repositories;

import micro.management.userservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
