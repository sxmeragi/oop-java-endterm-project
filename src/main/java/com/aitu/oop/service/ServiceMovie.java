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
        movieRepository.addMovie(movie);
    }

    // READ
    public List<Movie> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public Movie findMovieById(int id) {
        for (Movie movie : movieRepository.findAllMovies()) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    // UPDATE
    public boolean updateMovieRating(int movieId, double rating) {
        Movie movie = findMovieById(movieId);
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
}
