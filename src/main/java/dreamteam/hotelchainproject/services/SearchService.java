package dreamteam.hotelchainproject.services;

import dreamteam.hotelchainproject.dto.Search.SearchRequestDto;
import dreamteam.hotelchainproject.dto.Search.SearchResultDto;
import dreamteam.hotelchainproject.models.Room;
import dreamteam.hotelchainproject.models.RoomType;

import java.util.Date;
import java.util.List;

public interface SearchService {
    List<SearchResultDto> searchRooms(SearchRequestDto requestDto);
    Integer calculatePrice(RoomType roomType, Date dateMin, Date dateMax);
    Integer getAvailable(Integer roomtTypeId, Date dateMin, Date dateMax);
}
