package dreamteam.hotelchainproject.services;

import dreamteam.hotelchainproject.dto.RoomTypeDto;
import dreamteam.hotelchainproject.dto.season.NewPriceDto;
import dreamteam.hotelchainproject.dto.season.SeasonDto;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface SeasonService {
    ResponseEntity<SeasonDto> addNewSeason(String newSeasonName, Date startDate, Date endDate, String title, String text, List<NewPriceDto> prices);

    ResponseEntity<SeasonDto> deleteSeason(String seasonName);

    List<RoomTypeDto> getRoomTypesForHotel(int hotelId);

    List<SeasonDto> getSeasonsForHotel(int hotelId);

    List<NewPriceDto> getPricesForSeason(String seasonName, int hotelId);

}
