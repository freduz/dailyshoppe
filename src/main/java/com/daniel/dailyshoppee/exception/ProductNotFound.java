package com.daniel.dailyshoppee.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String message){
        super(message);
    }
}
