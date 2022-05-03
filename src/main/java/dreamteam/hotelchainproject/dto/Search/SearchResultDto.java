package dreamteam.hotelchainproject.dto.Search;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude
public class SearchResultDto {
    String hotelName;
    String hotelAddress;
    String roomTypeName;
    Integer capacity;
    Integer size;
    List<String> features;
    Integer price;
    Integer hotelId;
    Integer availableRoomCnt;
    Integer roomTypeId;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getAvailableRoomCnt() {
        return availableRoomCnt;
    }

    public void setAvailableRoomCnt(Integer availableRoomCnt) {
        this.availableRoomCnt = availableRoomCnt;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
