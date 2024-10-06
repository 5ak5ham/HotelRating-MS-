package micro.management.RatingService.Services;

import micro.management.RatingService.Entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRating();

    List<Rating> getRatingByUser(String userId);

    List<Rating> getRatingByHotel(String hotelId);
}
