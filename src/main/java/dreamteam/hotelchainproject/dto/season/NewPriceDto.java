package dreamteam.hotelchainproject.dto.season;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class NewPriceDto {
    int roomTypeId;
    String roomTypeName;
    int Monday;
    int Tuesday;
    int Wednesday;
    int Thursday;
    int Friday;
    int Saturday;
    int Sunday;

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getMonday() {
        return Monday;
    }

    public void setMonday(int monday) {
        Monday = monday;
    }

    public int getTuesday() {
        return Tuesday;
    }

    public void setTuesday(int tuesday) {
        Tuesday = tuesday;
    }

    public int getWednesday() {
        return Wednesday;
    }

    public void setWednesday(int wednesday) {
        Wednesday = wednesday;
    }

    public int getThursday() {
        return Thursday;
    }

    public void setThursday(int thursday) {
        Thursday = thursday;
    }

    public int getFriday() {
        return Friday;
    }

    public void setFriday(int friday) {
        Friday = friday;
    }

    public int getSaturday() {
        return Saturday;
    }

    public void setSaturday(int saturday) {
        Saturday = saturday;
    }

    public int getSunday() {
        return Sunday;
    }

    public void setSunday(int sunday) {
        Sunday = sunday;
    }
}
