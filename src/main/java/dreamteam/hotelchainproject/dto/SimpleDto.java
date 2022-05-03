package dreamteam.hotelchainproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class SimpleDto {
    private String message;
    private Integer age;

    public SimpleDto() {
    }

    public SimpleDto(String message, Integer age) {
        this.message = message;
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
