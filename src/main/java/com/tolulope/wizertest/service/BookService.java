package com.tolulope.wizertest.service;

import com.tolulope.wizertest.dto.request.BookDto;
import com.tolulope.wizertest.dto.response.BookResponse;
import com.tolulope.wizertest.model.Book;

import java.util.List;

public interface BookService {
    BookResponse saveBook(BookDto bookDto);
    BookResponse editBook(BookDto bookDto);
    List<Book> listBooks();
    BookResponse addBookToCategory(Long bookId, Long categoryId);
    List<Book> addToFavourites(Long bookId);
    void deleteBook(Long bookId);
}
