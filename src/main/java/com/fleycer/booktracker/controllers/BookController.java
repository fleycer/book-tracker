package com.fleycer.booktracker.controllers;

import com.fleycer.booktracker.dto.BookDTO;
import com.fleycer.booktracker.dto.BookListDTO;
import com.fleycer.booktracker.entities.Book;
import com.fleycer.booktracker.enums.ReadingStatus;
import com.fleycer.booktracker.servecies.BookService;
import com.fleycer.booktracker.util.ErrorUtil;
import com.fleycer.booktracker.util.exceptions.BookException;
import com.fleycer.booktracker.util.validators.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookService bookService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }

    @GetMapping("/all")
    public ResponseEntity<BookListDTO> index(){
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(new BookListDTO(books), HttpStatus.OK) ;
    }

    @GetMapping("/findby/name")
    public ResponseEntity<BookListDTO> findBooksByName(@RequestParam(value = "param") String name){
        List<Book> books = bookService.findByName(name);
        return new ResponseEntity<>(new BookListDTO(books), HttpStatus.OK) ;
    }

    @GetMapping("/findby/status")
    public ResponseEntity<BookListDTO> findBooksByReadingStatus(@RequestParam(value = "param") ReadingStatus readingStatus){
        List<Book> books = bookService.findByReadingStatus(readingStatus);
        return new ResponseEntity<>(new BookListDTO(books), HttpStatus.OK) ;
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

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateBook(@PathVariable Long id,
                                                 @RequestBody BookDTO bookDTO,
                                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BookException(ErrorUtil.bindingResultErrorsToString(bindingResult));
        }

        bookService.updateBook(id, bookDTO.convertToModel());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
