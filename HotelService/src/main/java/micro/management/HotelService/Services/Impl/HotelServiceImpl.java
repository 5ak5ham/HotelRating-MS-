package micro.management.HotelService.Services.Impl;

import micro.management.HotelService.Entities.Hotel;
import micro.management.HotelService.Repositories.HotelRepositoy;
import micro.management.HotelService.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import micro.management.HotelService.Exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepositoy hotelRepositoy;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);

        return hotelRepositoy.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepositoy.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepositoy.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found by Id :" + id));
    }
}
