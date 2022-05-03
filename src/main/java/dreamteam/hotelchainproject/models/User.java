package dreamteam.hotelchainproject.models;

import dreamteam.hotelchainproject.security.jwt.SignupRequest;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "email")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "identification_type")
    private String identificationType;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "password")
    private String password;

    public User() {}

    public User(SignupRequest request) {
        this.username = request.getUsername();
        this.password = request.getPassword();
        this.firstName = request.getFirstName();
        this.identificationType = request.getIdentificationType();
        this.identificationNumber = request.getIdentificationNumber();
        this.mobilePhone = request.getMobilePhone();
        this.address = request.getAddress();
        this.lastName = request.getLastName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
