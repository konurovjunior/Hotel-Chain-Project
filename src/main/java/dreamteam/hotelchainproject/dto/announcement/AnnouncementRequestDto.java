package dreamteam.hotelchainproject.dto.announcement;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AnnouncementRequestDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotNull
    private int hotelId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
