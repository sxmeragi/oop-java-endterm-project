package com.aitu.oop.controller;

import com.aitu.oop.entity.Genre;
import com.aitu.oop.entity.Movie;
import com.aitu.oop.service.ServiceMovie;

import java.util.List;
import java.util.Scanner;

public class MovieController {

    private final ServiceMovie serviceMovie;
    private final Scanner scanner = new Scanner(System.in);

    public MovieController(ServiceMovie serviceMovie) {
        this.serviceMovie = serviceMovie;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    showAllMovies();
                    break;
                case 3:
                    findMovieById();
                    break;
                case 4:
                    updateMovieRating();
                    break;
                case 5:
                    deleteMovie();
                    break;
                case 6:
                    findMoviesByGenre();
                    break;
                case 7:
                    findMoviesByTitle();
                    break;
                case 8:
                    findMoviesByReleaseYear();
                    break;
                case 9:
                    showTopRatedMovies();
                    break;
                case 10:
                    findMoviesWithRatingAbove();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Wrong choice");
            }

        }
    }

    private void printMenu() {
        System.out.println("===== MOVIE MENU =====");
        System.out.println("1. Add movie");
        System.out.println("2. Show all movies");
        System.out.println("3. Find movie by ID");
        System.out.println("4. Update movie rating");
        System.out.println("5. Delete movie");
        System.out.println("6. Find movies by genre");
        System.out.println("7. Find movies by title");
        System.out.println("8. Find movies by release year");
        System.out.println("9. Top rated movies");
        System.out.println("10. Movies with rating above");
        System.out.println("0. Exit");
        System.out.println("======================");
        System.out.print("Choose: ");
    }


    // ---------- CONTROLLER METHODS ----------

    private void addMovie() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Release year: ");
        int year = readInt();

        System.out.print("Rating: ");
        double rating = readDouble();

        System.out.print("Genre ID: ");
        int genreId = readInt();

        Genre genre = new Genre();
        genre.setId(genreId);

        Movie movie = new Movie(0, title, year, rating, genre);
        serviceMovie.addMovie(movie);

        System.out.println("Movie added");
    }

    private void showAllMovies() {
        printMovies(serviceMovie.findAllMovies());
    }

    private void findMovieById() {
        System.out.print("Movie ID: ");
        int id = readInt();

        Movie movie = serviceMovie.findMovieById(id);
        System.out.println(movie != null ? movie : "Movie not found");
    }

    private void updateMovieRating() {
        System.out.print("Movie ID: ");
        int id = readInt();

        System.out.print("New rating: ");
        double rating = readDouble();

        boolean updated = serviceMovie.updateMovieRating(id, rating);
        System.out.println(updated ? "Rating updated" : "Movie not found");
    }

    private void deleteMovie() {
        System.out.print("Movie ID: ");
        int id = readInt();

        boolean deleted = serviceMovie.deleteMovieById(id);
        System.out.println(deleted ? "Movie deleted" : "Movie not found");
    }

    private void findMoviesByGenre() {
        System.out.print("Genre ID: ");
        int genreId = readInt();

        printMovies(serviceMovie.findMoviesByGenre(genreId));
    }

    private void findMoviesByTitle() {
        System.out.print("Title contains: ");
        String title = scanner.nextLine();

        printMovies(serviceMovie.findMoviesByTitle(title));
    }

    private void findMoviesByReleaseYear() {
        System.out.print("Release year: ");
        int year = readInt();

        printMovies(serviceMovie.findMoviesByReleaseYear(year));
    }

    private void showTopRatedMovies() {
        printMovies(serviceMovie.findTopRatedMovies());
    }

    private void findMoviesWithRatingAbove() {
        System.out.print("Minimum rating: ");
        double rating = readDouble();

        printMovies(serviceMovie.findMoviesWithRatingAbove(rating));
    }

    // ---------- HELPERS ----------

    private void printMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("No movies found");
            return;
        }
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Enter a number: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Enter a number: ");
            }
        }
    }
}