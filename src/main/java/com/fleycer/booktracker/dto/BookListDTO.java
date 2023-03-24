package com.fleycer.booktracker.dto;

import com.fleycer.booktracker.entities.Book;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class BookListDTO implements DTO<List<Book>> {
    private List<BookDTO> books;

    public BookListDTO(List<Book> books) {
        this.books = books.stream().map(b -> new BookDTO(b)).collect(Collectors.toList());
    }

    public List<Book> convertToModel() {
        return List.copyOf(this.books.stream().map(b -> b.convertToModel()).collect(Collectors.toList()));
    }
}
