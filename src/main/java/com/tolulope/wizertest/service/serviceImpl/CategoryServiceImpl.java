package com.tolulope.wizertest.service.serviceImpl;

import com.tolulope.wizertest.dto.request.CategoryDto;
import com.tolulope.wizertest.dto.response.CategoryResponse;
import com.tolulope.wizertest.exception.ConflictException;
import com.tolulope.wizertest.exception.NotFoundException;
import com.tolulope.wizertest.model.Category;
import com.tolulope.wizertest.repository.CategoryRepository;
import com.tolulope.wizertest.service.CategoryService;
import com.tolulope.wizertest.utils.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper mapper;

    @Override
    public CategoryResponse addCategory(CategoryDto categoryDto) {
        if(repository.existsCategoryByName(categoryDto.getName())) {
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, "Category with name already exists");
        }
        Category category = mapper.map(categoryDto, Category.class);
        Category savedCategory = repository.save(category);
        return mapper.map(savedCategory, CategoryResponse.class);
    }

    @Override
    public CategoryResponse editCategory(CategoryDto categoryDto) {
        Category category = repository.findById(categoryDto.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Requested Category Id does not exist"));
        mapper.map(categoryDto, category);
        repository.save(category);
        return mapper.map(category, CategoryResponse.class);
    }

    @Override
    public List<Category> listCategories() {
        return repository.findAll();
    }

}
