package dreamteam.hotelchainproject.dto.season;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude
public class SeasonDto {
    String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    Date minDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    Date maxDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
}
