package dreamteam.hotelchainproject.models;

import javax.persistence.*;

@Entity
@IdClass(RoomPK.class)
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_number")
    private int roomNumber;

    @Id
    @Column(name = "hotel_id")
    private int hotelId;

    @Column(name = "floor")
    private int floor;

    @Column(name = "is_clean")
    private String isClean;

    @Column(name = "room_type_id")
    private int roomTypeId;

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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getIsClean() {
        return isClean;
    }

    public void setIsClean(String isClean) {
        this.isClean = isClean;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

}
