package dreamteam.hotelchainproject.dto.announcement;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude
public class AnnouncementDto {
    private String title;
    private String text;
    private Date date;
    private String author;
    private String hotel;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
}
