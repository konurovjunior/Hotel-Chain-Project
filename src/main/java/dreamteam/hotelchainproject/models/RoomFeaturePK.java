package dreamteam.hotelchainproject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class RoomFeaturePK implements Serializable {
    private int roomTypeId;
    private String feature;

    @Column(name = "room_type_id")
    @Id
    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Column(name = "feature")
    @Id
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
        RoomFeaturePK that = (RoomFeaturePK) o;
        return roomTypeId == that.roomTypeId &&
                Objects.equals(feature, that.feature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomTypeId, feature);
    }
}
