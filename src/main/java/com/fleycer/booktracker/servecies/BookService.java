package com.fleycer.booktracker.servecies;

import com.fleycer.booktracker.entities.Book;
import com.fleycer.booktracker.entities.Genre;
import com.fleycer.booktracker.repositories.BookRepository;
import com.fleycer.booktracker.repositories.GenreRepository;
import com.fleycer.booktracker.util.exceptions.GenreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    @Autowired
    public BookService(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }
    public List<Book> findByName(String name) {
        List<Book> books = bookRepository.findByName(name);
        return books;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return books;
    }

    @Transactional
    public void saveBook(Book book) throws GenreException {
        Genre genre = genreRepository
                .findByName(book.getGenre().getName())
                .orElseThrow(()-> new GenreException("Genre is not found in data base for the book"));
        book.setGenre(genre);
        bookRepository.save(book);
    }
}
