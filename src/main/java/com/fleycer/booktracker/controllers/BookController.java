package com.fleycer.booktracker.controllers;

import com.fleycer.booktracker.dto.BookDTO;
import com.fleycer.booktracker.dto.BookListDTO;
import com.fleycer.booktracker.entities.Book;
import com.fleycer.booktracker.servecies.BookService;
import com.fleycer.booktracker.util.ErrorUtil;
import com.fleycer.booktracker.util.exceptions.BookException;
import com.fleycer.booktracker.util.ErrorResponse;
import com.fleycer.booktracker.util.exceptions.GenreException;
import com.fleycer.booktracker.util.validators.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookService bookService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }

    @GetMapping("/find")
    public BookListDTO findBookByName(@RequestParam(value = "name") String name){
        List<Book> books = bookService.findByName(name);
        return new BookListDTO(books);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addBook(@RequestBody BookDTO bookDTO, BindingResult bindingResult){
        Book book = bookDTO.convertToModel();

        bookValidator.validate(book,bindingResult);

        if(bindingResult.hasErrors()){
            throw new BookException(ErrorUtil.bindingResultErrorsToString(bindingResult));
        }

        bookService.saveBook(bookDTO.convertToModel());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BookException bookException){
        ErrorResponse errorResponse = new ErrorResponse(bookException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(GenreException genreException){
        ErrorResponse errorResponse = new ErrorResponse(genreException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
