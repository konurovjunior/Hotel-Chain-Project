package dreamteam.hotelchainproject.servicesImpl;

import dreamteam.hotelchainproject.dto.booking.RoomBookingDto;
import dreamteam.hotelchainproject.dto.profile.ReservationDto;
import dreamteam.hotelchainproject.models.Hotel;
import dreamteam.hotelchainproject.models.Reservation;
import dreamteam.hotelchainproject.models.Room;
import dreamteam.hotelchainproject.models.RoomType;
import dreamteam.hotelchainproject.repositories.*;
import dreamteam.hotelchainproject.models.RoomAssignment;
import dreamteam.hotelchainproject.services.ReservationService;
import dreamteam.hotelchainproject.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    private RoomAssignmentRepository roomAssignmentRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    SearchService searchService;

    @Override
    public List<ReservationDto> getUserProfileReservationsPast(String username) {
        List<Reservation> reservations = reservationRepository.findAllByGuestEmail(username);
        List<ReservationDto> dtos = new ArrayList<>();
        Date currentDate = new Date();
        for (Reservation reservation : reservations){
            if (reservation.getCheckOutDate().getTime()<currentDate.getTime())
                dtos.add(mapEntityToDto(reservation));
        }
        return dtos;
    }

    @Override
    public List<ReservationDto> getUserProfileReservationsActive(String username) {
        List<Reservation> reservations = reservationRepository.findAllByGuestEmail(username);
        List<ReservationDto> dtos = new ArrayList<>();
        Date currentDate = new Date();
        for (Reservation reservation : reservations){
            if (reservation.getCheckOutDate().getTime()>=currentDate.getTime()
            && reservation.getCheckInDate().getTime()<=currentDate.getTime())
                dtos.add(mapEntityToDto(reservation));
        }
        return dtos;
    }

    @Override
    public List<ReservationDto> getUserProfileReservationsFuture(String username) {
        List<Reservation> reservations = reservationRepository.findAllByGuestEmail(username);
        List<ReservationDto> dtos = new ArrayList<>();
        Date currentDate = new Date();
        for (Reservation reservation : reservations){
            if (reservation.getCheckInDate().getTime()>currentDate.getTime())
                dtos.add(mapEntityToDto(reservation));
        }
        return dtos;
    }

    @Override
    @Transactional
    public Long cancelReservation(int id) {
        roomAssignmentRepository.deleteAllByReservationId(id);
        Long delCnt = reservationRepository.deleteByReservationId(id);
        return delCnt;
    }


    ReservationDto mapEntityToDto(Reservation reservation){
        ReservationDto dto = new ReservationDto();
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setFinalPrice(reservation.getFinalPrice());
        dto.setReservationId(reservation.getReservationId());
        dto.setRoomCount(reservation.getRoomCount());
        RoomType roomType = roomTypeRepository.findByRoomTypeId(reservation.getRoomTypeId());
        dto.setRoomTypeName(roomType.getName());
        Hotel hotel = hotelRepository.findById(roomType.getHotelId()).get();
        dto.setHotelName(hotel.getName());
        return dto;
    }

    @Override
    public Reservation bookRoom(RoomBookingDto data, String user) {
        RoomType roomType = roomTypeRepository.getByRoomTypeId(data.getRoomTypeId());
        data.setPrice(searchService.calculatePrice(roomType, data.getCheckIn(), data.getCheckOut()));
        Reservation reservation = reservationRepository.saveAndFlush(mapDtoToReservation(data, user));
        return reservation;
    }
    Reservation mapDtoToReservation(RoomBookingDto data, String user) {
        Reservation entity = new Reservation();
        entity.setFinalPrice(data.getPrice() * data.getRoomCount());
        entity.setGuestEmail(user);
        entity.setRoomCount(data.getRoomCount());
        entity.setRoomTypeId(data.getRoomTypeId());
        entity.setCheckInDate(data.getCheckIn());
        entity.setCheckOutDate(data.getCheckOut());
        return entity;
    }
}
