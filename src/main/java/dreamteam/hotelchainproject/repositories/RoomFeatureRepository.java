package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.RoomFeature;
import dreamteam.hotelchainproject.models.RoomFeaturePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomFeatureRepository extends JpaRepository<RoomFeature, RoomFeaturePK> {
    List<RoomFeature> findAllByRoomTypeId(Integer roomTypeId);
}
