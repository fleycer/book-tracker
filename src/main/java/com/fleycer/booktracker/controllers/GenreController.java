package com.fleycer.booktracker.controllers;

import com.fleycer.booktracker.dto.GenreDTO;
import com.fleycer.booktracker.servecies.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addGenre(@RequestBody GenreDTO genreDTO){
        genreService.addGenre(genreDTO.convertToModel());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGenre(@PathVariable("id") Long id){
        genreService.deleteGenre(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
