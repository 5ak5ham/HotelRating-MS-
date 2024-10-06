package micro.management.HotelService.Services;

import micro.management.HotelService.Entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotel();

    Hotel getHotel(String id);

}
