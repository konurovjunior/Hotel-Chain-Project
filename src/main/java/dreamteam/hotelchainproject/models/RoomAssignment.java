package dreamteam.hotelchainproject.models;

import java.sql.Date;
import javax.persistence.*;

@Entity
@IdClass(RoomAssignmentPK.class)
@Table(name = "room_assignment")
public class RoomAssignment {

    @Id
    @Column(name = "room_number")
    private int roomNumber;

    @Id
    @Column(name = "hotel_id")
    private int hotelId;

    @Id
    @Column(name = "guest_email")
    private String guestEmail;

    @Id
    @Column(name = "reservation_id")
    private int reservationId;

    @Column(name = "check_in_date")
    private Date checkInDate;

    @Column(name = "check_out_date")
    private Date checkOutDate;

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

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

}
