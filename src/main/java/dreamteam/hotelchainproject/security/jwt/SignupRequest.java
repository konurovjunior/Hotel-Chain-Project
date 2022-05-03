package dreamteam.hotelchainproject.security.jwt;
import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
    @NotBlank(message = "username must not be blank")
    @Size(min = 3, max = 45, message = "username must have size between 3 and 20")
    private String username;

    @NotBlank(message = "password must not be blank")
    @Size(min = 6, max = 40, message = "password must have length between 6 and 40")
    private String password;

    @NotBlank(message = "first name must not be blank")
    private String firstName;

    @NotBlank(message = "identification type must not be blank")
    private String identificationType;

    @NotBlank(message = "identification number must not be blank")
    private String identificationNumber;

    @NotBlank(message = "mobile phone must not be blank")
    private String mobilePhone;

    @NotBlank(message = "address must not be blank")
    private String address;

    @NotBlank(message = "last name must not be blank")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
