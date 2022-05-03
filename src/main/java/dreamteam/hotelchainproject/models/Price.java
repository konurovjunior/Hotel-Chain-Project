package dreamteam.hotelchainproject.models;

import javax.persistence.*;

@Entity
@IdClass(PricePK.class)
@Table(name = "price")
public class Price {

    @Id
    @Column(name = "season_name")
    private String seasonName;

    @Id
    @Column(name = "room_type_id")
    private int roomTypeId;

    @Column(name = "monday")
    private int monday;

    @Column(name = "tuesday")
    private int tuesday;

    @Column(name = "wednesday")
    private int wednesday;

    @Column(name = "thursday")
    private int thursday;

    @Column(name = "friday")
    private int friday;

    @Column(name = "saturday")
    private int saturday;

    @Column(name = "sunday")
    private int sunday;

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

    public int getMonday() {
        return monday;
    }

    public void setMonday(int monday) {
        this.monday = monday;
    }

    public int getTuesday() {
        return tuesday;
    }

    public void setTuesday(int tuesday) {
        this.tuesday = tuesday;
    }

    public int getWednesday() {
        return wednesday;
    }

    public void setWednesday(int wednesday) {
        this.wednesday = wednesday;
    }

    public int getThursday() {
        return thursday;
    }

    public void setThursday(int thursday) {
        this.thursday = thursday;
    }

    public int getFriday() {
        return friday;
    }

    public void setFriday(int friday) {
        this.friday = friday;
    }

    public int getSaturday() {
        return saturday;
    }

    public void setSaturday(int saturday) {
        this.saturday = saturday;
    }

    public int getSunday() {
        return sunday;
    }

    public void setSunday(int sunday) {
        this.sunday = sunday;
    }

}
