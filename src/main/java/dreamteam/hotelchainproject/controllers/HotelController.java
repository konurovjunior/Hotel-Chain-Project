package dreamteam.hotelchainproject.controllers;

import dreamteam.hotelchainproject.dto.hotel.HotelDto;
import dreamteam.hotelchainproject.models.Hotel;
import dreamteam.hotelchainproject.models.RoomType;
import dreamteam.hotelchainproject.repositories.HotelRepository;
import dreamteam.hotelchainproject.repositories.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @GetMapping("/hotels/list")
    public ResponseEntity<List<Hotel>> getListOfHotels() {
        return ResponseEntity.ok().body(hotelRepository.findAll());
    }

    @GetMapping("/hotel/roomtypes")
    public ResponseEntity<List<RoomType>> getListOfRoomTypes(@RequestParam(value = "id") int hotelId) {
        return ResponseEntity.ok().body(roomTypeRepository.findAllByHotelId(hotelId));
    }

}
