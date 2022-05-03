package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.Room;
import dreamteam.hotelchainproject.models.RoomPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, RoomPK> {
    Room getByRoomNumberAndAndHotelId(int roomNumber, int hotelId);
    int countAllByRoomTypeId(int roomTypeId);
    List<Room> findAll();
}
