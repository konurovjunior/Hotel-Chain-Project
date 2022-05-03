package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    List<Reservation> findAllByGuestEmail(String guestEmail);
    Long deleteByReservationId(int id);
    List<Reservation> findAllByRoomTypeId(int roomTypeId);

    @Query(value = "select sum(room_count) from reservation where room_type_id=:roomTypeId", nativeQuery = true)
    int totalRoomCountByRoomTypeId(@Param("roomTypeId") int roomTypeId);
}
