package dreamteam.hotelchainproject.dto.hotel;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class HotelDto {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
