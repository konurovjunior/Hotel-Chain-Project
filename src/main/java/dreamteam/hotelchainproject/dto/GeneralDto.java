package dreamteam.hotelchainproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class GeneralDto {
    String message;
    Boolean success;

    public GeneralDto(){

    }

    public GeneralDto(String message, Boolean success){
        this.message=message;
        this.success=success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
