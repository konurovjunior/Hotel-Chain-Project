package dreamteam.hotelchainproject.models;

import javax.persistence.*;

@Entity
@IdClass(HotelFeaturePK.class)
@Table(name = "hotel_feature")
public class HotelFeature {

    @Id
    @Column(name = "hotel_id")
    private int hotelId;

    @Id
    @Column(name = "feature")
    private String feature;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
