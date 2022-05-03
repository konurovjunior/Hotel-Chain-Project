package dreamteam.hotelchainproject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class RoomPK implements Serializable {
    private int roomNumber;
    private int hotelId;

    @Column(name = "room_number")
    @Id
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Column(name = "hotel_id")
    @Id
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomPK roomPK = (RoomPK) o;
        return roomNumber == roomPK.roomNumber &&
                hotelId == roomPK.hotelId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, hotelId);
    }
}
