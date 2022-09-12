package com.tolulope.wizertest.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    private String code;

    public NotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }


}
