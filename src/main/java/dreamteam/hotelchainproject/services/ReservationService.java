package dreamteam.hotelchainproject.services;

import dreamteam.hotelchainproject.dto.booking.RoomBookingDto;
import dreamteam.hotelchainproject.dto.profile.ReservationDto;
import dreamteam.hotelchainproject.models.Reservation;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> getUserProfileReservationsPast(String username);
    List<ReservationDto> getUserProfileReservationsActive(String username);
    List<ReservationDto> getUserProfileReservationsFuture(String username);
    Long cancelReservation(int id);
    Reservation bookRoom(RoomBookingDto data, String user);
}
