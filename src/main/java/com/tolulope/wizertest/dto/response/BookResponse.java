package com.tolulope.wizertest.dto.response;

import com.tolulope.wizertest.model.Category;
import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String name;
    private Category category;
}
