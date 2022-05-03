package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.HotelHasSeason;
import dreamteam.hotelchainproject.models.HotelHasSeasonPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelHasSeasonRepository extends JpaRepository<HotelHasSeason, HotelHasSeasonPK> {
    List<HotelHasSeason> findAllByHotelId(Integer hotelId);
    void deleteAllBySeasonName(String seasonName);
}
