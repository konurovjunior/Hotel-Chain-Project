package dreamteam.hotelchainproject.controllers;

import dreamteam.hotelchainproject.dto.RoomTypeDto;
import dreamteam.hotelchainproject.dto.season.NewPriceDto;
import dreamteam.hotelchainproject.dto.season.SeasonDto;
import dreamteam.hotelchainproject.repositories.EmployeeRespository;
import dreamteam.hotelchainproject.services.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/season")
public class SeasonController {

    @Autowired
    SeasonService seasonService;

    @Autowired
    EmployeeRespository employeeRespository;


    @PostMapping("/create")
    ResponseEntity<SeasonDto> addNewSeason(@RequestParam(required = true) String newSeasonName, @RequestParam(required = true) Date startDate,
                                           @RequestParam(required = true) Date endDate, @RequestParam(required = false) String title, @RequestParam(required = false) String text,
                                           @RequestBody(required = true) List<NewPriceDto> prices){
        return seasonService.addNewSeason(newSeasonName, startDate, endDate, title, text, prices);
    }

    @DeleteMapping("/delete")
    ResponseEntity<SeasonDto> deleteSeason(@RequestParam(required = true) String seasonName){
        return seasonService.deleteSeason(seasonName);
    }

    @GetMapping("/get-room-types")
    List<RoomTypeDto> getRoomTypesForMyHotel(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        int hotelId = employeeRespository.findById(username).get().getHotelId();
        return seasonService.getRoomTypesForHotel(hotelId);
    }

    @GetMapping("/get-my-seasons")
    List<SeasonDto> getMySeasons(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        int hotelId = employeeRespository.findById(username).get().getHotelId();
        return seasonService.getSeasonsForHotel(hotelId);
    }

    @GetMapping("/get-season-price")
    List<NewPriceDto> getPricesForSeason(@RequestParam String seasonName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        int hotelId = employeeRespository.findById(username).get().getHotelId();
        return seasonService.getPricesForSeason(seasonName, hotelId);
    }

}
