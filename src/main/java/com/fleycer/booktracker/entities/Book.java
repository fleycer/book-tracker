package com.fleycer.booktracker.entities;

import com.fleycer.booktracker.enums.ReadingStatus;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "Book")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "author")
    private String author;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private ReadingStatus readingStatus;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    public Book(String author, String name, ReadingStatus readingStatus, Genre genre) {
        this.author = author;
        this.name = name;
        this.readingStatus = readingStatus;
        this.genre = genre;
    }
}
