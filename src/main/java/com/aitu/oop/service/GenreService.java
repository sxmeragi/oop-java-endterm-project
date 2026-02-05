package com.aitu.oop.service;

import java.util.List;

import com.aitu.oop.entity.Genre;
import com.aitu.oop.repository.GenreRepository;

public class GenreService {
    
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAllGenres();
    }

    public Genre getGenreByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Genre name cannot be empty");
        }
        return genreRepository.findByName(name);
    }
}
