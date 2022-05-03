package dreamteam.hotelchainproject.dto.employee;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalTime;

@JsonInclude
public class EmployeeDto {
    private String username;
    private String type;
    private String workingDays;
    private LocalTime workingFrom;
    private LocalTime workingTill;
    private int hourlyRate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

    public LocalTime getWorkingFrom() {
        return workingFrom;
    }

    public void setWorkingFrom(LocalTime workingFrom) {
        this.workingFrom = workingFrom;
    }

    public LocalTime getWorkingTill() {
        return workingTill;
    }

    public void setWorkingTill(LocalTime workingTill) {
        this.workingTill = workingTill;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
