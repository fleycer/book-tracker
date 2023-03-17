package com.fleycer.booktracker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Genre")
@NoArgsConstructor @AllArgsConstructor @Data
public class Genre implements Model{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public Genre(String name) {
        this.name = name;
    }
}
