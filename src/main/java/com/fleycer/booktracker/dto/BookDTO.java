package com.fleycer.booktracker.dto;

import com.fleycer.booktracker.entities.Book;
import com.fleycer.booktracker.entities.Genre;
import com.fleycer.booktracker.enums.ReadingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDTO implements DTO<Book>{
    private String author;
    private String name;
    private ReadingStatus readingStatus;
    private Genre genre;

    public BookDTO(Book book){
        this.author = book.getAuthor();
        this.name = book.getName();
        this.readingStatus = book.getReadingStatus();
        this.genre = book.getGenre();
    }

    @Override
    public Book convertToModel() {
        return new Book(author, name, readingStatus, genre);
    }
}
