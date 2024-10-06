package micro.management.userservice.Services.Impl;

import micro.management.userservice.Entities.User;
import micro.management.userservice.Exceptions.ResourceNotFoundException;
import micro.management.userservice.Repositories.UserRepository;
import micro.management.userservice.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setUserId(id);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String userId) {

        Optional<User> newUser = userRepository.findById(userId);
        if (newUser.isEmpty()) throw new ResourceNotFoundException("User with given id does not exist");
        else return newUser;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User with given id does not exist"));

        updatedUser.setName(user.getName());
        updatedUser.setAbout(user.getAbout());
        updatedUser.setEmail(user.getEmail());

        userRepository.save(updatedUser);
        return updatedUser;
    }
}
