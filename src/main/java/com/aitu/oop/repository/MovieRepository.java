package com.aitu.oop.repository;

import java.util.ArrayList;
import java.util.List;

import com.aitu.oop.entity.Movie;

public class MovieRepository {

    private List<Movie> movies = new ArrayList<>();

    public MovieRepository() {
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> findAllMovies() {
        return new ArrayList<>(movies);
    }

    public List<Movie> findMoviesByGenre(int genreId) {
        List<Movie> result = new ArrayList<>();
        for (Movie m : movies) {
            if (m.getGenre().getId() == genreId) {
                result.add(m);
            }
        }
        return result;
    }

    public List<Movie> findTopRatedMovies() {
        List<Movie> sorted = new ArrayList<>(movies);
        sorted.sort((m1, m2) -> Double.compare(m2.getRating(), m1.getRating()));
        return sorted.size() > 10 ? sorted.subList(0, 10) : sorted;
    }
}
