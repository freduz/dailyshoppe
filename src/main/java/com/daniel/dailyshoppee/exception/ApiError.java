package com.daniel.dailyshoppee.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class ApiError {

    private HttpStatus status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String debugMessage;
    private List<SubError> subErrors = new ArrayList<>();;

    private ApiError(){
      this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status){
        super();
        this.status = status;
    }

    public ApiError(HttpStatus status,Throwable ex){
        this();
        this.message = "Unexpected Error occurred";
        this.status = status;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status,Throwable ex,String message){
        super();
        this.status = status;
        this.debugMessage = ex.getLocalizedMessage();
        this.message = message;
    }

    public void addValidationErrors(List<FieldError> fieldErrors){
        fieldErrors.forEach(this::addValidationError);
    }

    public void addValidationError(FieldError fieldError){
       addValidationError(fieldError.getObjectName(),fieldError.getField(),fieldError.getRejectedValue(),fieldError.getDefaultMessage());
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new SubError(object, field, rejectedValue, message));
    }


    public void addSubError(SubError subError){
        if(subErrors == null){
            subErrors = new ArrayList<>();
        }

        subErrors.add(subError);
    }
}
