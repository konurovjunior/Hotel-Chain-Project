package dreamteam.hotelchainproject.models;

import javax.persistence.*;

@Entity
@IdClass(RoomFeaturePK.class)
@Table(name = "room_feature")
public class RoomFeature {

    @Id
    @Column(name = "room_type_id")
    private int roomTypeId;

    @Id
    @Column(name = "feature")
    private String feature;

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

}
