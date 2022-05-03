package dreamteam.hotelchainproject.dto.booking;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@JsonInclude
public class RoomBookingDto {

    @NotNull(message = "hotel must not be null")
    private int hotelId;

    private String guest;

    private int price;

    @NotNull(message = "room type must not be null")
    @Min(value = 1, message = "room type id must be positive")
    private int roomTypeId;

    @NotNull(message = "roomCount must not be null")
    @Min(value = 1, message = "room count must be positive")
    private int roomCount;

    @NotNull(message = "check in date must not be null")
    private Date checkIn;

    @NotNull(message = "check out date must not be null")
    private Date checkOut;

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}
