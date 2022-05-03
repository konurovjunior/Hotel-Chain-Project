package dreamteam.hotelchainproject.dto.booking;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class RoomDto {
    int roomNumber;
    int hotelId;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
