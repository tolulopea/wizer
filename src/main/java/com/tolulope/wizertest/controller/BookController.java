package com.tolulope.wizertest.controller;

import com.tolulope.wizertest.dto.response.Response;
import com.tolulope.wizertest.dto.request.BookDto;
import com.tolulope.wizertest.service.BookService;
import com.tolulope.wizertest.utils.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping("")
    public ResponseEntity<Response> createBook(@RequestBody BookDto request) {
        Response response = new Response();
        response.setCode(CustomResponseCode.CREATED);
        response.setData(service.saveBook(request));
        response.setMessage("Successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> editBook(@RequestBody BookDto request) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(service.editBook(request));
        response.setMessage("Update Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Response> listBooks() {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(service.listBooks());
        response.setMessage("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/addToCategory")
    public ResponseEntity<Response> addToCategory(@RequestParam(value = "bookId") Long bookId,
                                             @RequestParam(value = "categoryId") Long categoryId) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(service.addBookToCategory(bookId, categoryId));
        response.setMessage("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/addToFavourites")
    public ResponseEntity<Response> addToFavourites(@RequestParam(value = "bookId") Long bookId) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(service.addToFavourites(bookId));
        response.setMessage("Book Added Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping ("/{bookId}")
    public ResponseEntity<Response> deleteBook(@PathVariable Long bookId) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        service.deleteBook(bookId);
        response.setMessage("Successful") ;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
