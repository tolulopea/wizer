package com.tolulope.wizertest.service.serviceImpl;

import com.tolulope.wizertest.dto.request.BookDto;
import com.tolulope.wizertest.dto.response.BookResponse;
import com.tolulope.wizertest.exception.ConflictException;
import com.tolulope.wizertest.exception.NotFoundException;
import com.tolulope.wizertest.model.Book;
import com.tolulope.wizertest.model.Category;
import com.tolulope.wizertest.repository.BookRepository;
import com.tolulope.wizertest.repository.CategoryRepository;
import com.tolulope.wizertest.service.BookService;
import com.tolulope.wizertest.utils.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    private final List<Book> favouriteBooks = new ArrayList<>();

    @Override
    public BookResponse saveBook(BookDto bookDto) {
        if(repository.existsBookByName(bookDto.getName())) {
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION, "Book with name already exists");
        }
        Book book = mapper.map(bookDto, Book.class);
        if(Objects.nonNull(bookDto.getCategoryId())) {
            Category category = categoryRepository.findById(bookDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Requested Category Id does not exist"));
            book.setCategory(category);
        }
        Book savedBook = repository.save(book);
        return mapper.map(savedBook, BookResponse.class);
    }

    @Override
    public BookResponse editBook(BookDto bookDto) {
        Book bookExists = repository.findById(bookDto.getId())
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Requested Book Id does not exist"));
        Book book = mapper.map(bookDto, Book.class);
        if(Objects.nonNull(bookDto.getCategoryId())){
            Category category = categoryRepository.findById(bookDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Requested Category Id does not exist"));
            book.setCategory(category);
        }else {
            book.setCategory(bookExists.getCategory());
        }
        repository.save(book);
        return mapper.map(book, BookResponse.class);
    }

    @Override
    public List<Book> listBooks() {
        return repository.findAll();
    }

    @Override
    public BookResponse addBookToCategory(Long bookId, Long categoryId) {
        Book book = repository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Requested Book Id does not exist"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Requested Category Id does not exist"));
        book.setCategory(category);
        repository.save(book);
        return mapper.map(book, BookResponse.class);
    }

    @Override
    public List<Book> addToFavourites(Long bookId) {
        Book book = repository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Requested Book Id does not exist"));
        favouriteBooks.add(book);
        return favouriteBooks;
    }

    @Override
    public void deleteBook(Long bookId) {
        repository.deleteById(bookId);
    }
}
