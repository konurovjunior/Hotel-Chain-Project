package dreamteam.hotelchainproject.models;

import javax.persistence.*;

@Entity
@IdClass(HotelHasSeasonPK.class)
@Table(name = "hotel_has_season")

public class HotelHasSeason {

    @Id
    @Column(name = "hotel_id")
    private int hotelId;

    @Id
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

}
