package com.tolulope.wizertest.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }

    private String code;

    public ConflictException(String code, String message) {
        super(message);
        this.code = code;
    }
}
