package com.aitu.oop.controller;

import java.util.Scanner;

import com.aitu.oop.entity.Genre;
import com.aitu.oop.entity.Movie;
import com.aitu.oop.service.GenreService;
import com.aitu.oop.service.ServiceMovie;

public class MovieController {

    private final ServiceMovie serviceMovie;
    private final GenreService genreService;
    private final Scanner scanner = new Scanner(System.in);

    public MovieController(ServiceMovie serviceMovie, GenreService genreService) {
        this.serviceMovie = serviceMovie;
        this.genreService = genreService;
    }

    public void showMovies() {
        System.out.println("");
        serviceMovie.findAllMovies().forEach((movie) -> {
            System.out.println(movie.getId() + ". " + movie.getTitle() +
                    "(" + movie.getRating() + ")"); 
                });
    }

    public void showMoviesByGenre(){

        System.out.println("");
        genreService.getAllGenres().forEach((genre)->{
            System.out.println(genre.getId() + ". " + genre.getName());
        });
        System.out.print("GenreID:");
        int genreId = Integer.parseInt(scanner.nextLine());
        serviceMovie.findMoviesByGenre(genreId).forEach((movie) -> {
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

        Genre genreObj = genreService.getGenreByName(genre);

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