package com.daniel.dailyshoppee.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

}
