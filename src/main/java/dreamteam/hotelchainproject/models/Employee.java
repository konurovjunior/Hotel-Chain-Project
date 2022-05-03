package dreamteam.hotelchainproject.models;

import dreamteam.hotelchainproject.dto.employee.EmployeeInfoDto;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "type")
    private String type;

    @Column(name = "hotel_id")
    private int hotelId;

    @Column(name = "working_days")
    private String workingDays;

    @Column(name = "working_from")
    private LocalTime from;

    @Column(name = "working_till")
    private LocalTime till;

    @Column(name = "hourly_rate")
    private int hourlyRate;

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

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    public Employee update(EmployeeInfoDto dto) {
        if(dto.getEmail() != null) setEmail(dto.getEmail());
        if(dto.getFrom() != null) setFrom(dto.getFrom());
        if(dto.getTill() != null) setTill(dto.getTill());
        if(dto.getHourlyRate() != null) setHourlyRate(dto.getHourlyRate());
        if(dto.getWorkingDays() != null) setWorkingDays(dto.getWorkingDays());
        if(dto.getType() != null) setType(dto.getType());
        return this;
    }
}
