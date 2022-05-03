package dreamteam.hotelchainproject.servicesImpl;

import dreamteam.hotelchainproject.dto.booking.BookingFulfillmentDto;
import dreamteam.hotelchainproject.dto.booking.RoomDto;
import dreamteam.hotelchainproject.models.*;
import dreamteam.hotelchainproject.repositories.ReservationRepository;
import dreamteam.hotelchainproject.repositories.RoomAssignmentRepository;
import dreamteam.hotelchainproject.repositories.RoomRepository;
import dreamteam.hotelchainproject.repositories.RoomTypeRepository;
import dreamteam.hotelchainproject.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomAssignmentRepository roomAssignmentRepository;

    @Autowired
    SearchServiceImpl searchService;

    @Override
    public List<RoomDto> availableRooms(int reservationId) {
        List<RoomDto> result = new ArrayList<>();
        Reservation reservation = reservationRepository.findById(reservationId).get();
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms){
            if (room.getRoomTypeId()==reservation.getRoomTypeId() && !searchService.isRoomAssigned(room, reservation.getCheckInDate(), reservation.getCheckOutDate())){
                result.add(roomToDto(room));
            }
        }
        return result;
    }

    @Override
    public List<RoomDto> assignedRooms(int reservationId) {
        List<RoomDto> result = new ArrayList<>();
        List<RoomAssignment> assignments = roomAssignmentRepository.findAll();
        for (RoomAssignment assignment : assignments){
            if (assignment.getReservationId()==reservationId) {
                RoomDto dto = new RoomDto();
                dto.setRoomNumber(assignment.getRoomNumber());
                dto.setHotelId(assignment.getHotelId());
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public void createAssignment(int reservationId, int roomNumber) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        RoomType roomType = roomTypeRepository.findByRoomTypeId(reservation.getRoomTypeId());
        RoomAssignment roomAssignment = new RoomAssignment();
        roomAssignment.setCheckInDate(reservation.getCheckInDate());
        roomAssignment.setCheckOutDate(reservation.getCheckOutDate());
        roomAssignment.setGuestEmail(reservation.getGuestEmail());
        roomAssignment.setHotelId(roomType.getHotelId());
        roomAssignment.setReservationId(reservationId);
        roomAssignment.setRoomNumber(roomNumber);
        roomAssignmentRepository.save(roomAssignment);
    }

    @Override
    public void deleteAssignment(int reservationId, int roomNumber) {
        List<RoomAssignment> roomAssignments = roomAssignmentRepository.findAll();
        for (RoomAssignment assignment : roomAssignments){
            if (assignment.getReservationId()==reservationId && assignment.getRoomNumber()==roomNumber){
                RoomAssignmentPK pk = new RoomAssignmentPK();
                pk.setGuestEmail(assignment.getGuestEmail());
                pk.setHotelId(assignment.getHotelId());
                pk.setReservationId(assignment.getReservationId());
                pk.setRoomNumber(assignment.getRoomNumber());
                roomAssignmentRepository.deleteById(pk);
                return;
            }
        }
    }

    @Override
    public List<BookingFulfillmentDto> getReservationsByHotelId(int hotelId) {
        List<BookingFulfillmentDto> result = new ArrayList<>();
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations){
            RoomType roomType = roomTypeRepository.getByRoomTypeId(reservation.getRoomTypeId());
            if (roomType.getHotelId()==hotelId){
                result.add(bookingToDto(reservation));
            }
        }
        return result;
    }

    RoomDto roomToDto(Room room){
        RoomDto dto = new RoomDto();
        dto.setHotelId(room.getHotelId());
        dto.setRoomNumber(room.getRoomNumber());
        return dto;
    }

    BookingFulfillmentDto bookingToDto(Reservation booking){
        BookingFulfillmentDto dto = new BookingFulfillmentDto();
        RoomType roomType = roomTypeRepository.getByRoomTypeId(booking.getRoomTypeId());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setFinalPrice(booking.getFinalPrice());
        dto.setGuestEmail(booking.getGuestEmail());
        dto.setReservationId(booking.getReservationId());
        dto.setRoomCnt(booking.getRoomCount());
        dto.setRoomTypeId(booking.getRoomTypeId());
        dto.setRoomTypeName(roomType.getName());
        return dto;
    }
}
