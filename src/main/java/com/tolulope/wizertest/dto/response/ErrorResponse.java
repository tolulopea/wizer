package com.tolulope.wizertest.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;
}
