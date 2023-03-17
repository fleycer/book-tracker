package com.fleycer.booktracker.dto;

import com.fleycer.booktracker.entities.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenreDTO implements DTO<Genre>{
    private String name;

    public GenreDTO(Genre genre){
        this.name = genre.getName();
    }

    @Override
    public Genre convertToModel() {
        return new Genre(name);
    }
}
