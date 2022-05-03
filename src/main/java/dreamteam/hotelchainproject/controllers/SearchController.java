package dreamteam.hotelchainproject.controllers;

import dreamteam.hotelchainproject.dto.Search.SearchRequestDto;
import dreamteam.hotelchainproject.dto.Search.SearchResultDto;
import dreamteam.hotelchainproject.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    SearchService searchService;

    @PostMapping("/search-rooms")
    List<SearchResultDto> searchRooms(@Valid @RequestBody SearchRequestDto requestDto){
        return searchService.searchRooms(requestDto);
    }

}
