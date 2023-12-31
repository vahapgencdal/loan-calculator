package com.tekbit.loan.calculator.payload.response;

import java.io.Serial;
import java.io.Serializable;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({
        "success",
        "message"
})
@Builder
public class GenericResponse implements Serializable {

    @Serial
    @JsonIgnore
    private static final long serialVersionUID = 7702134516418120340L;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    @JsonIgnore
    private HttpStatus status;

    public GenericResponse() {

    }

    public GenericResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public GenericResponse(Boolean success, String message, HttpStatus httpStatus) {
        this.success = success;
        this.message = message;
        this.status = httpStatus;
    }
}