package micro.management.userservice.Services.Impl;

import micro.management.userservice.Entities.Hotel;
import micro.management.userservice.Entities.Rating;
import micro.management.userservice.Entities.User;
import micro.management.userservice.Exceptions.ResourceNotFoundException;
import micro.management.userservice.External.Services.HotelService;
import micro.management.userservice.Repositories.UserRepository;
import micro.management.userservice.Services.UserService;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


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
    public User getUserById(String userId) {

        Optional<User> newUser = userRepository.findById(userId);

        if (newUser.isEmpty()) throw new ResourceNotFoundException("User with given id does not exist");
        else {
            User user = newUser.get();

            // Method 1

            Rating[] ratingArr = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + userId, Rating[].class);

            List<Rating> ratings = Arrays.stream(ratingArr).toList();

            /* Method 2

              ResponseEntity<List<Rating>> responseEntity =
                      restTemplate.exchange("http://RATINGSERVICE/ratings/users/" + userId,
                              HttpMethod.GET, null, new ParameterizedTypeReference<List<Rating>>() {
                              });

              List<Rating> ratings = responseEntity.getBody();

             */

            List<Rating> ratingList = ratings.stream().map(rating -> {
//  Hotel hotel = restTemplate.getForObject("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingList);

            return user;
        }
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
