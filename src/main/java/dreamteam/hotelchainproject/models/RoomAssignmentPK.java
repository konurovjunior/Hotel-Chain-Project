package dreamteam.hotelchainproject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class RoomAssignmentPK implements Serializable {
    private int roomNumber;
    private int hotelId;
    private String guestEmail;
    private int reservationId;

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

    @Column(name = "guest_email")
    @Id
    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    @Column(name = "reservation_id")
    @Id
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAssignmentPK that = (RoomAssignmentPK) o;
        return roomNumber == that.roomNumber &&
                hotelId == that.hotelId &&
                reservationId == that.reservationId &&
                Objects.equals(guestEmail, that.guestEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, hotelId, guestEmail, reservationId);
    }
}
