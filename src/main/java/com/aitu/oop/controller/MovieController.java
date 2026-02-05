package com.aitu.oop.controller;

import java.util.Scanner;

import com.aitu.oop.entity.Genre;
import com.aitu.oop.entity.Movie;
import com.aitu.oop.repository.GenreRepository;
import com.aitu.oop.service.ServiceMovie;

public class MovieController {

    private final ServiceMovie serviceMovie;
    private final GenreRepository genreRepository;
    private final Scanner scanner = new Scanner(System.in);

    public MovieController(ServiceMovie serviceMovie, GenreRepository genreRepository) {
        this.serviceMovie = serviceMovie;
        this.genreRepository = genreRepository;
    }

    public void showMovies() {
        serviceMovie.findAllMovies().forEach((movie) -> {
            System.out.println(movie.getId() + ". " + movie.getTitle() +
                    "(" + movie.getRating() + ")");

        });
    }

    public void addMovie() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Release year");
        int rYear = Integer.parseInt(scanner.nextLine());

        System.out.print("Rating");
        double rating = Double.parseDouble(scanner.nextLine());

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        Genre genreObj = genreRepository.findByName(genre);

        serviceMovie.addMovie(new Movie(title,
                rYear, rating,genreObj ));

        System.out.println("Movie added");
    }

    public void deleteMovie() {
        showMovies();

        System.out.print("Enter moive ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        serviceMovie.deleteMovieById(id);
        System.out.println("Succes. Movie deleted");

    }

}