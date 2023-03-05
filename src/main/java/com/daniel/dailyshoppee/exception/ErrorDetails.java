package com.daniel.dailyshoppee.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ErrorDetails {
    private String message;
    private LocalDate timestamp;
    private String description;

    public ErrorDetails(String message,LocalDate timestamp,String description){
        super();
        this.message = message;
        this.timestamp =timestamp;
        this.description = description;
    }
}
