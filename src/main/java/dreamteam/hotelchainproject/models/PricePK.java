package dreamteam.hotelchainproject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class PricePK implements Serializable {

    @Column(name = "season_name")
    private String seasonName;

    @Column(name = "room_type_id")
    private int roomTypeId;

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricePK pricePK = (PricePK) o;
        return roomTypeId == pricePK.roomTypeId &&
                Objects.equals(seasonName, pricePK.seasonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonName, roomTypeId);
    }
}
