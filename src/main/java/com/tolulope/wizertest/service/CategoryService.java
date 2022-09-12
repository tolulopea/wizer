package com.tolulope.wizertest.service;

import com.tolulope.wizertest.dto.request.CategoryDto;
import com.tolulope.wizertest.dto.response.CategoryResponse;
import com.tolulope.wizertest.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryDto categoryDto);
    CategoryResponse editCategory(CategoryDto categoryDto);
    List<Category> listCategories();
}
