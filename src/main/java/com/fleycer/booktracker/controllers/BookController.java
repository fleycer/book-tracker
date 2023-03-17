package com.fleycer.booktracker.controllers;

import com.fleycer.booktracker.dto.BookDTO;
import com.fleycer.booktracker.servecies.BookService;
import com.fleycer.booktracker.util.exceptions.BookException;
import com.fleycer.booktracker.util.ErrorResponse;
import com.fleycer.booktracker.util.exceptions.GenreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addBook(@RequestBody BookDTO bookDTO){
        bookService.saveBook(bookDTO.convertToModel());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BookException bookException){
        ErrorResponse errorResponse = new ErrorResponse(bookException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse>  handleException(GenreException genreException){
        ErrorResponse errorResponse = new ErrorResponse(genreException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
