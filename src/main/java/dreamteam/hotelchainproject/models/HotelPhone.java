package dreamteam.hotelchainproject.models;

import javax.persistence.*;

@Entity
@IdClass(HotelPhonePK.class)
@Table(name = "hotel_phone")
public class HotelPhone {

    @Id
    @Column(name = "hotel_id")
    private int hotelId;

    @Id
    @Column(name = "phone")
    private String phone;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
