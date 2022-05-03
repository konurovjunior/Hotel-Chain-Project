package dreamteam.hotelchainproject.servicesImpl;

import dreamteam.hotelchainproject.dto.Search.SearchRequestDto;
import dreamteam.hotelchainproject.dto.Search.SearchResultDto;
import dreamteam.hotelchainproject.models.*;
import dreamteam.hotelchainproject.repositories.*;
import dreamteam.hotelchainproject.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    public static final String normalSeasonName = "Normal";

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomAssignmentRepository roomAssignmentRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomFeatureRepository roomFeatureRepository;

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    HotelHasSeasonRepository hotelHasSeasonRepository;

    @Autowired
    PriceRepository priceRepository;

    @Override
    public List<SearchResultDto> searchRooms(SearchRequestDto requestDto) {
        Date dateMin = requestDto.getCheckInDate();
        Date dateMax = requestDto.getCheckOutDate();
        List<SearchResultDto> results = new ArrayList<>();
        List<Room> allRooms = roomRepository.findAll();
        HashMap<Integer,Integer> roomTypeCnt = new HashMap<>();
        HashMap<Integer,Integer> priceMap = new HashMap<>();
        Set<Integer> roomTypeIds = new HashSet<>();
        for (Room room : allRooms){
            if (isRoomAssigned(room, dateMin, dateMax))
                continue;
            if (!isRoomWanted(room, requestDto))
                continue;
            Integer price = calculatePrice(roomTypeRepository.getByRoomTypeId(room.getRoomTypeId()), dateMin, dateMax);
            if (requestDto.getPriceMax()!=null && price>requestDto.getPriceMax())
                continue;
            if (requestDto.getPriceMin()!=null && price<requestDto.getPriceMin())
                continue;
            priceMap.put(room.getRoomTypeId(), price);
            Integer cur = roomTypeCnt.get(room.getRoomTypeId());
            if (cur==null)
                roomTypeCnt.put(room.getRoomTypeId(), 1);
            else
                roomTypeCnt.put(room.getRoomTypeId(), cur+1);
            roomTypeIds.add(room.getRoomTypeId());
        }
        Iterator<Integer> it = roomTypeIds.iterator();
        while (it.hasNext()){
            Integer id = it.next();
            roomTypeCnt.put(id, roomTypeCnt.get(id)-reserveCnt(id, requestDto.getCheckInDate(), requestDto.getCheckOutDate()));
            if (roomTypeCnt.get(id)!=0)
                results.add(roomTypeToDto(id, priceMap.get(id), roomTypeCnt.get(id)));
        }
        return results;
    }

    @Override
    public Integer getAvailable(Integer roomTypeId, Date dateMin, Date dateMax) {
        List<Room> allRooms = roomRepository.findAll();
        HashMap<Integer,Integer> roomTypeCnt = new HashMap<>();
        HashMap<Integer,Integer> priceMap = new HashMap<>();
        Set<Integer> roomTypeIds = new HashSet<>();
        for (Room room : allRooms){
            if (isRoomAssigned(room, dateMin, dateMax))
                continue;
            Integer price = calculatePrice(roomTypeRepository.getByRoomTypeId(room.getRoomTypeId()), dateMin, dateMax);
            priceMap.put(room.getRoomTypeId(), price);
            Integer cur = roomTypeCnt.get(room.getRoomTypeId());
            if (cur==null)
                roomTypeCnt.put(room.getRoomTypeId(), 1);
            else
                roomTypeCnt.put(room.getRoomTypeId(), cur+1);
            roomTypeIds.add(room.getRoomTypeId());
        }
        Iterator<Integer> it = roomTypeIds.iterator();
        while (it.hasNext()){
            Integer id = it.next();
            roomTypeCnt.put(id, roomTypeCnt.get(id)-reserveCnt(id, dateMin, dateMax));
        }
        return roomTypeCnt.get(roomTypeId);
    }

    public Boolean isRoomAssigned(Room room, Date dateMin, Date dateMax){
        List<RoomAssignment> assignments = roomAssignmentRepository.findAll();
        for (RoomAssignment assignment : assignments){
            if (assignment.getRoomNumber()==room.getRoomNumber() && assignment.getHotelId()==room.getHotelId()){
                if (!(assignment.getCheckInDate().getTime()>dateMax.getTime() || assignment.getCheckOutDate().getTime()<dateMin.getTime())){
                    return true;
                }
            }
        }
        return false;
    }

    Integer reserveCnt(Integer roomTypeId, Date dateMin, Date dateMax){
        Integer cnt=0;
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations){
            if (reservation.getCheckInDate().getTime()>dateMax.getTime())
                continue;
            if (reservation.getCheckOutDate().getTime()<dateMin.getTime())
                continue;
            if (reservation.getRoomTypeId()==roomTypeId)
                cnt+=reservation.getRoomCount();
        }
        return cnt;
    }

    // Does not include price check
    Boolean isRoomWanted(Room room, SearchRequestDto dto){
//        String roomTypeName = roomTypeRepository.findByRoomTypeId(room.getRoomTypeId()).getName();
        if (dto.getHotelId()!=null && room.getHotelId()!=dto.getHotelId())
            return false;
        if (dto.getRoomTypeId()!=null && !dto.getRoomTypeId().equals(room.getRoomTypeId()))
            return false;
        return true;
    }

    @Override
    public Integer calculatePrice(RoomType roomType, Date dateMin, Date dateMax){
        Integer total=0;
        Calendar cal = Calendar.getInstance();
        Date cur = dateMin;
        while (cur.getTime()<dateMax.getTime()){
            List<HotelHasSeason> hotelHasSeasons = hotelHasSeasonRepository.findAllByHotelId(roomType.getHotelId());
            Season matchingSeason = null;
            for (HotelHasSeason rel : hotelHasSeasons){
                Season season = seasonRepository.findByName(rel.getSeasonName());
                if (cur.getTime()<season.getStartDate().getTime() || cur.getTime()>=season.getEndDate().getTime())
                    continue;
                matchingSeason = season;
                break;
            }
            if (matchingSeason==null)
                matchingSeason = seasonRepository.findByName(normalSeasonName);
            Price price = priceRepository.findByRoomTypeIdAndSeasonName(roomType.getRoomTypeId(), matchingSeason.getName());
            cal.setTime(cur);
            Integer day = cal.get(Calendar.DAY_OF_WEEK);
            if (day==1)
                total+=price.getSunday();
            else if (day==2)
                total+=price.getMonday();
            else if (day==3)
                total+=price.getTuesday();
            else if (day==4)
                total+=price.getWednesday();
            else if (day==5)
                total+=price.getThursday();
            else if (day==6)
                total+=price.getFriday();
            else
                total+=price.getSaturday();
            Long newTime = cur.getTime()+ TimeUnit.DAYS.toMillis(1);
            cur = new Date(newTime);
        }
        return total;
    }

    SearchResultDto roomTypeToDto(Integer id, Integer price, Integer availableRoomCnt){
        RoomType roomType = roomTypeRepository.findByRoomTypeId(id);
        SearchResultDto dto = new SearchResultDto();
        dto.setRoomTypeName(roomType.getName());
        dto.setCapacity(roomType.getCapacity());
        dto.setAvailableRoomCnt(availableRoomCnt);
        dto.setPrice(price);
        dto.setSize(roomType.getSize());
        Hotel hotel = hotelRepository.findById(roomType.getHotelId()).get();
        dto.setHotelAddress(hotel.getAddress());
        dto.setHotelId(hotel.getId());
        dto.setHotelName(hotel.getName());
        dto.setRoomTypeId(roomType.getRoomTypeId());
        List<RoomFeature> features = roomFeatureRepository.findAllByRoomTypeId(id);
        List<String> featureList = features.stream().map(f -> f.getFeature()).collect(Collectors.toList());
        dto.setFeatures(featureList);
        return dto;
    }
}
