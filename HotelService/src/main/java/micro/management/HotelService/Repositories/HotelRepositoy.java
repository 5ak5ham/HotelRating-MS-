package micro.management.HotelService.Repositories;

import micro.management.HotelService.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepositoy extends JpaRepository<Hotel, String> {
}
