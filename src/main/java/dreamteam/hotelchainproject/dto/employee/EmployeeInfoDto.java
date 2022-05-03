package dreamteam.hotelchainproject.dto.employee;

import java.sql.Time;
import java.time.LocalTime;

public class EmployeeInfoDto {
    private String email;
    private String type;
    private String workingDays;
    private LocalTime from;
    private LocalTime till;
    private Integer hourlyRate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTill() {
        return till;
    }

    public void setTill(LocalTime till) {
        this.till = till;
    }

    public Integer getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
