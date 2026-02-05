package com.aitu.oop.service;

import java.util.ArrayList;
import java.util.List;

import com.aitu.oop.entity.Movie;
import com.aitu.oop.repository.MovieRepository;

public class ServiceMovie {

    private final MovieRepository movieRepository;

    public ServiceMovie(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // CREATE
    public void addMovie(Movie movie) {
        validateMovie(movie);
        movieRepository.addMovie(movie);
    }

    // READ
    public Movie findMovieById(int id) {
        validateMovieId(id);
        return movieRepository.findMovieById(id);
    }

    // UPDATE
    public boolean updateMovieRating(int movieId, double rating) {
        validateMovieId(movieId);
        validateRating(rating);

        Movie movie = movieRepository.findMovieById(movieId);
        if (movie == null) {
            return false;
        }
        movie.setRating(rating);
        return true;
    }

    // DELETE
    public boolean deleteMovieById(int id) {
        return movieRepository.deleteMovieById(id);
    }

    // SEARCH & FILTERS
    public List<Movie> findMoviesByGenre(int genreId) {
        return movieRepository.findMoviesByGenre(genreId);
    }

    public List<Movie> findMoviesByTitle(String title) {
       return movieRepository.findAllMovies()
                .stream()
               .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
               .toList();
    }

    public List<Movie> findMoviesByReleaseYear(int year) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movieRepository.findAllMovies()) {
            if (movie.getReleaseYear() == year) {
                result.add(movie);
            }
        }
        return result;
    }

    public List<Movie> findTopRatedMovies() {
        return movieRepository.findTopRatedMovies();
    }

    public List<Movie> findMoviesWithRatingAbove(double rating) {
       return movieRepository.findAllMovies()
               .stream()
               .filter(movie -> movie.getRating() >  rating)
               .toList();
    }

private void validateMovie(Movie movie) {
    if (movie == null) {
        throw new IllegalArgumentException("Movie cannot be null");
    }

    if (movie.getTitle() == null || movie.getTitle().trim().isEmpty()) {
        throw new IllegalArgumentException("Movie title cannot be empty");
    }

    if (movie.getReleaseYear() < 1888 || movie.getReleaseYear() > 2100) {
        throw new IllegalArgumentException("Invalid release year");
    }

    if (movie.getRating() < 0 || movie.getRating() > 10) {
        throw new IllegalArgumentException("Rating must be between 0 and 10");
    }

    if (movie.getGenre() == null) {
        throw new IllegalArgumentException("Genre must not be null");
    }
}

private void validateMovieId(int id) {
    if (id <= 0) {
        throw new IllegalArgumentException("Invalid movie ID");
    }
}

private void validateRating(double rating) {
    if (rating < 0 || rating > 10) {
        throw new IllegalArgumentException("Rating must be between 0 and 10");
    }
}
}
