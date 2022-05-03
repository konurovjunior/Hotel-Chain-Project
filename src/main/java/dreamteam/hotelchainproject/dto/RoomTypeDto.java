package dreamteam.hotelchainproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class RoomTypeDto {

    int roomTypeId;
    String roomTypeName;

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

}
