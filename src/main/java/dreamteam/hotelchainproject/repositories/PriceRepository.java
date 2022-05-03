package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.Price;
import dreamteam.hotelchainproject.models.PricePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, PricePK> {
    Price findByRoomTypeIdAndSeasonName(Integer roomTypeId, String seasonName);
    void deleteAllBySeasonName(String seasonName);
}
