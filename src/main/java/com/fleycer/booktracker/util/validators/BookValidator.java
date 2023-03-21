package com.fleycer.booktracker.util.validators;

import com.fleycer.booktracker.entities.Book;
import com.fleycer.booktracker.servecies.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private BookService bookService;

    @Autowired
    public BookValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book)target;
        //For one author can be only one unique book name. Check this
        boolean isThereSimilarBookNameForAuthor = bookService.findByAuthor(book.getAuthor()).stream()
                .anyMatch(b -> b.getName().equals(book.getName()));
        if(isThereSimilarBookNameForAuthor){
            errors.rejectValue("name", "For one author can be only one unique book name");
        }
    }
}
