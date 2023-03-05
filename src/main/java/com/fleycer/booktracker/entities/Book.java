package com.fleycer.booktracker.entities;

import com.fleycer.booktracker.enums.ReadingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
