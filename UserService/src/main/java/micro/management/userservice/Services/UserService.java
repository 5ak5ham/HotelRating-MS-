package micro.management.userservice.Services;

import micro.management.userservice.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    Optional<User> getUserById(String userId);

    void deleteUser(String userId);

    User updateUser(User user);

}
