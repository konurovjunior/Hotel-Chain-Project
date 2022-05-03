package dreamteam.hotelchainproject.servicesImpl;

import dreamteam.hotelchainproject.dto.RoomTypeDto;
import dreamteam.hotelchainproject.dto.season.NewPriceDto;
import dreamteam.hotelchainproject.dto.season.SeasonDto;
import dreamteam.hotelchainproject.models.*;
import dreamteam.hotelchainproject.repositories.*;
import dreamteam.hotelchainproject.services.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    HotelHasSeasonRepository hotelHasSeasonRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRespository employeeRespository;

    @Autowired
    AnnouncementRespository announcementRespository;

    @Override
    public ResponseEntity<SeasonDto> addNewSeason(String newSeasonName, Date startDate, Date endDate, String title, String text, List<NewPriceDto> prices) {
        RoomType roomType = roomTypeRepository.findByRoomTypeId(prices.get(0).getRoomTypeId());
        int hotelId = roomType.getHotelId();
        SeasonDto newSeasonDto = new SeasonDto();
        newSeasonDto.setMaxDate(endDate); newSeasonDto.setMinDate(startDate); newSeasonDto.setName(newSeasonName);
        List<HotelHasSeason> seasons = hotelHasSeasonRepository.findAllByHotelId(hotelId);
        for (HotelHasSeason hotelHasSeason : seasons){
            Season season = seasonRepository.findByName(hotelHasSeason.getSeasonName());
            if (!(season.getStartDate().getTime()>newSeasonDto.getMaxDate().getTime() || season.getEndDate().getTime()<newSeasonDto.getMinDate().getTime())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        Season season = new Season();
        season.setName(newSeasonDto.getName());
        season.setStartDate(newSeasonDto.getMinDate());
        season.setEndDate(newSeasonDto.getMaxDate());
        seasonRepository.save(season);
        HotelHasSeason hotelHasSeason = new HotelHasSeason();
        hotelHasSeason.setHotelId(hotelId);
        hotelHasSeason.setSeasonName(season.getName());
        hotelHasSeasonRepository.save(hotelHasSeason);
        for (NewPriceDto dto : prices){
            Price price = new Price();
            price.setRoomTypeId(dto.getRoomTypeId());
            price.setSeasonName(season.getName());
            price.setMonday(dto.getMonday()); price.setTuesday(dto.getTuesday());
            price.setWednesday(dto.getWednesday()); price.setThursday(dto.getThursday());
            price.setFriday(dto.getFriday()); price.setSaturday(dto.getSaturday()); price.setSunday(dto.getSunday());
            priceRepository.save(price);
        }
        announcementRespository.save(createAnnouncement(title, newSeasonName, text, startDate, endDate, prices));
        return ResponseEntity.ok(newSeasonDto);
    }

    @Override
    @Transactional
    public ResponseEntity<SeasonDto> deleteSeason(String seasonName) {
        if (seasonRepository.findByName(seasonName)==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        hotelHasSeasonRepository.deleteAllBySeasonName(seasonName);
        priceRepository.deleteAllBySeasonName(seasonName);
        seasonRepository.deleteById(seasonName);
        return ResponseEntity.ok(null);
    }

    @Override
    public List<RoomTypeDto> getRoomTypesForHotel(int hotelId) {
        List<RoomType> roomTypes = roomTypeRepository.findAllByHotelId(hotelId);
        List<RoomTypeDto> dtos = new ArrayList<>();
        for (RoomType roomType : roomTypes)
            dtos.add(roomTypeToDto(roomType));
        return dtos;
    }

    @Override
    public List<SeasonDto> getSeasonsForHotel(int hotelId) {
        List<SeasonDto> result = new ArrayList<>();
        List<HotelHasSeason> seasons = hotelHasSeasonRepository.findAllByHotelId(hotelId);
        for (HotelHasSeason hotelHasSeason : seasons){
            Season season = seasonRepository.findByName(hotelHasSeason.getSeasonName());
            result.add(seasonToDto(season));
        }
        return result;
    }

    @Override
    public List<NewPriceDto> getPricesForSeason(String seasonName, int hotelId) {
        List<NewPriceDto> result = new ArrayList<>();
        List<RoomType> roomTypes = roomTypeRepository.findAllByHotelId(hotelId);
        for (RoomType roomType : roomTypes){
            Price price = priceRepository.findByRoomTypeIdAndSeasonName(roomType.getRoomTypeId(), seasonName);
            result.add(priceToDto(price));
        }
        return result;
    }


    RoomTypeDto roomTypeToDto(RoomType roomType){
        RoomTypeDto dto = new RoomTypeDto();
        dto.setRoomTypeId(roomType.getRoomTypeId());
        dto.setRoomTypeName(roomType.getName());
        return dto;
    }

    NewPriceDto priceToDto(Price price){
        NewPriceDto dto = new NewPriceDto();
        dto.setFriday(price.getFriday());
        dto.setMonday(price.getMonday());
        dto.setSaturday(price.getSaturday());
        dto.setSunday(price.getSunday());
        dto.setThursday(price.getThursday());
        dto.setTuesday(price.getTuesday());
        dto.setWednesday(price.getWednesday());
        dto.setRoomTypeId(price.getRoomTypeId());
        int id = price.getRoomTypeId();
        dto.setRoomTypeName(roomTypeRepository.getByRoomTypeId(id).getName());
        return dto;
    }

    Announcement createAnnouncement(String title, String seasonName, String text, Date startDate, Date endDate, List<NewPriceDto> prices){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();
        Announcement announcement = new Announcement();
        announcement.setAuthor(username);
        announcement.setDate(new Date());
        Employee employee = employeeRespository.findById(username).get();
        announcement.setHotelId(employee.getHotelId());
        if (title==null)
            title=seasonName+" season opening soon!";
        if (text==null){
            text="We are happy to annouce that "+seasonName+" season is added to our hotel. The details are provided below.\n";
            text+="Start date: "+startDate.toString()+"\n";
            text+="End date: "+endDate.toString()+"\n";
        }
        announcement.setText(text);
        announcement.setTitle(title);
        return announcement;
    }

    SeasonDto seasonToDto(Season season){
        SeasonDto dto = new SeasonDto();
        dto.setMinDate(season.getStartDate());
        dto.setMaxDate(season.getEndDate());
        dto.setName(season.getName());
        return dto;
    }

}
