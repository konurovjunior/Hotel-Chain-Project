package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    RoomType getByRoomTypeId(int roomTypeId);
    RoomType findByRoomTypeId(Integer id);
    List<RoomType> findAllByHotelId(Integer hotelId);
}
