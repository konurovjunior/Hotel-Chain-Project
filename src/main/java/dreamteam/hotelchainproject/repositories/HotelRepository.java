package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    Hotel getById(int hotelId);
}
