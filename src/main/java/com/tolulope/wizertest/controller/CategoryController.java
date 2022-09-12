package com.tolulope.wizertest.controller;

import com.tolulope.wizertest.dto.response.Response;
import com.tolulope.wizertest.dto.request.CategoryDto;
import com.tolulope.wizertest.service.CategoryService;
import com.tolulope.wizertest.utils.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping("")
    public ResponseEntity<Response> createCategory(@RequestBody CategoryDto request) {
        Response response = new Response();
        response.setCode(CustomResponseCode.CREATED);
        response.setData(service.addCategory(request));
        response.setMessage("Successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> editCategory(@RequestBody CategoryDto request) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(service.editCategory(request));
        response.setMessage("Update Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Response> getAllCategory() {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(service.listCategories());
        response.setMessage("Categories fetched successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
