package com.fleycer.booktracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Genre")
@NoArgsConstructor @AllArgsConstructor  @Getter  @Setter
public class Genre {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public Genre(String name) {
        this.name = name;
    }
}
