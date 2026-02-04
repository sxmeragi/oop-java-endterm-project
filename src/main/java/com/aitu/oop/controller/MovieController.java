package com.aitu.oop.controller;

import java.util.Scanner;

import com.aitu.oop.entity.Genre;
import com.aitu.oop.entity.Movie;
import com.aitu.oop.service.ServiceMovie;

public class MovieController {

    private final ServiceMovie serviceMovie;
    private final Scanner scanner = new Scanner(System.in);

    public MovieController(ServiceMovie serviceMovie) {
        this.serviceMovie = serviceMovie;
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

        serviceMovie.addMovie(new Movie(title,
                rYear, rating, new Genre(genre)));

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