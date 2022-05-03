package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRespository extends JpaRepository<Announcement, Integer> {
    List<Announcement> getAllByHotelIdOrderByDate(int hotelId);
    List<Announcement> getAllByOrderByDate();
    Announcement getByIdAndAuthor(int id, String email);
}
