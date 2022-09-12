package com.tolulope.wizertest.dto.request;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String name;
    private Long categoryId;
}
