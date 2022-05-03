package dreamteam.hotelchainproject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class HotelHasSeasonPK implements Serializable {

    @Column(name = "hotel_id")
    private int hotelId;

    @Column(name = "season_name")
    private String seasonName;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelHasSeasonPK that = (HotelHasSeasonPK) o;
        return hotelId == that.hotelId &&
                Objects.equals(seasonName, that.seasonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, seasonName);
    }
}
