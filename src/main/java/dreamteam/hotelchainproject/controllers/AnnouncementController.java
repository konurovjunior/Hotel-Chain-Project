package dreamteam.hotelchainproject.controllers;

import dreamteam.hotelchainproject.dto.announcement.AnnouncementDto;
import dreamteam.hotelchainproject.dto.announcement.AnnouncementRequestDto;
import dreamteam.hotelchainproject.models.Announcement;
import dreamteam.hotelchainproject.repositories.AnnouncementRespository;
import dreamteam.hotelchainproject.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AnnouncementController {

    @Autowired
    private AnnouncementRespository announcementRespository;
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/announcements/get")
    public ResponseEntity<List<AnnouncementDto>> getHotelAnnouncements(@RequestParam(value = "hotel", required = false) Optional<Integer> hotelId) {
        if(hotelId.isPresent()) {
            return ResponseEntity.ok(mapListToDto(announcementRespository.getAllByHotelIdOrderByDate(hotelId.get())));
        } else {
            return ResponseEntity.ok(mapListToDto(announcementRespository.getAllByOrderByDate()));
        }
    }
    @PostMapping("/announcements/create")
    public ResponseEntity<AnnouncementDto> createHotelAnnouncement(@Valid @RequestBody AnnouncementRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Announcement announcement = mapRequestDtoToAnnouncement(requestDto);
        announcement.setAuthor(username);
        announcementRespository.saveAndFlush(announcement);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapAnnouncementToDto(announcement));
    }
    @PostMapping("/announcements/delete")
    public ResponseEntity<Void> deleteHotelAnnouncement(@RequestParam(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Announcement announcement = announcementRespository.getByIdAndAuthor(id, username);
        if(announcement == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        announcementRespository.delete(announcement);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    private Announcement mapRequestDtoToAnnouncement(AnnouncementRequestDto requestDto) {
        Announcement announcement = new Announcement();
        announcement.setDate(new Date());
        announcement.setHotelId(requestDto.getHotelId());
        announcement.setText(requestDto.getText());
        announcement.setTitle(requestDto.getTitle());
        return announcement;
    }
    private AnnouncementDto mapAnnouncementToDto(Announcement announcement) {
        AnnouncementDto dto = new AnnouncementDto();
        dto.setAuthor(announcement.getAuthor());
        dto.setDate(announcement.getDate());
        dto.setText(announcement.getText());
        dto.setTitle(announcement.getTitle());
        dto.setHotel(hotelRepository.getById(announcement.getHotelId()).getName());
        return dto;
    }
    private List<AnnouncementDto> mapListToDto(List<Announcement> list) {
        List<AnnouncementDto> result = new ArrayList<>();
        for(Announcement announcement: list) {
            result.add(mapAnnouncementToDto(announcement));
        }
        return result;
    }
}
