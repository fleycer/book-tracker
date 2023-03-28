package com.fleycer.booktracker.servecies;

import com.fleycer.booktracker.entities.Genre;
import com.fleycer.booktracker.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    public void addGenre(Genre genre){
        genreRepository.save(genre);
    }

    public Optional<Genre> findByName(String name){
        return genreRepository.findByName(name);
    }

    @Transactional
    public void deleteGenre(Long id){
        genreRepository.deleteById(id);
    }
}
