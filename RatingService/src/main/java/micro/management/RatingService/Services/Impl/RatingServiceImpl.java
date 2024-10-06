package micro.management.RatingService.Services.Impl;

import micro.management.RatingService.Entities.Rating;
import micro.management.RatingService.Repositories.RatingRepository;
import micro.management.RatingService.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUser(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotel(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
