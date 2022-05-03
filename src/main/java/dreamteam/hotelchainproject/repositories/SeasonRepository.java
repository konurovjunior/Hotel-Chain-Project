package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season,String> {
    Season findByName(String name);
}
