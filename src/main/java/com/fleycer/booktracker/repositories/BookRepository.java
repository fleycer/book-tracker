package com.fleycer.booktracker.repositories;

import com.fleycer.booktracker.entities.Book;
import com.fleycer.booktracker.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(Genre genre);
}
