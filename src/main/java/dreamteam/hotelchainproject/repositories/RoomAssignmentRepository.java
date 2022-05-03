package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.RoomAssignment;
import dreamteam.hotelchainproject.models.RoomAssignmentPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomAssignmentRepository extends JpaRepository<RoomAssignment, RoomAssignmentPK> {
    void deleteAllByReservationId(int reservationId);
}
