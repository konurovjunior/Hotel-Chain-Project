package dreamteam.hotelchainproject.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

public class HotelFeaturePK implements Serializable {

    @Column(name = "hotel_id")
    private int hotelId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelFeaturePK that = (HotelFeaturePK) o;
        return hotelId == that.hotelId &&
                Objects.equals(feature, that.feature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, feature);
    }
}
