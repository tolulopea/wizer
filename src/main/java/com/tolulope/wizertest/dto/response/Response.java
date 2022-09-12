package com.tolulope.wizertest.dto.response;

import lombok.Data;

@Data
public class Response {
    private String code;
    private Object data;
    private String message;
}
