package dreamteam.hotelchainproject.services;

import dreamteam.hotelchainproject.dto.booking.BookingFulfillmentDto;
import dreamteam.hotelchainproject.dto.booking.RoomDto;

import java.util.List;

public interface AssignmentService {
    List<RoomDto> availableRooms(int reservationId);

    List<RoomDto> assignedRooms(int reservationId);

    void createAssignment(int reservationId, int roomNumber);

    void deleteAssignment(int reservationId, int roomNumber);

    List<BookingFulfillmentDto> getReservationsByHotelId(int hotelId);

}
