package dreamteam.hotelchainproject.controllers;

import dreamteam.hotelchainproject.dto.booking.BookingFulfillmentDto;
import dreamteam.hotelchainproject.dto.booking.RoomDto;
import dreamteam.hotelchainproject.repositories.EmployeeRespository;
import dreamteam.hotelchainproject.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    EmployeeRespository employeeRespository;

    @GetMapping("/available-rooms")
    List<RoomDto> getAvailableRooms(@RequestParam(required = true) int reservationId){
        return assignmentService.availableRooms(reservationId);
    }

    @GetMapping("/assigned-rooms")
    List<RoomDto> getAssignedRooms(@RequestParam(required = true) int reservationId){
        return assignmentService.assignedRooms(reservationId);
    }

    @PostMapping("/create")
    void createAssignment(@RequestParam(required = true) int reservationId, @RequestParam(required = true) int roomNumber){
        assignmentService.createAssignment(reservationId, roomNumber);
    }

    @DeleteMapping("/delete")
    void deleteAssignment(@RequestParam(required = true) int reservationId, @RequestParam(required = true) int roomNumber){
        assignmentService.deleteAssignment(reservationId, roomNumber);
    }

    @GetMapping("/all-reservations")
    List<BookingFulfillmentDto> getAllReservations(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        int hotelId = employeeRespository.findById(username).get().getHotelId();
        return assignmentService.getReservationsByHotelId(hotelId);
    }

}
