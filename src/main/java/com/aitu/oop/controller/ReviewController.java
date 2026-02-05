package com.aitu.oop.controller;

import java.util.Scanner;

import com.aitu.oop.entity.Review;
import com.aitu.oop.entity.User;
import com.aitu.oop.service.ReviewService;
import com.aitu.oop.service.ServiceMovie;

public class ReviewController {
    private final ServiceMovie serviceMovie;
    private final ReviewService reviewService;
    private final Scanner scanner = new Scanner(System.in);

    public ReviewController(ServiceMovie serviceMovie, ReviewService reviewService) {
        this.serviceMovie = serviceMovie;
        this.reviewService = reviewService;

    }

    public void addReview(User user) {
        serviceMovie.findAllMovies().forEach(movie -> {
            System.out.println(movie.getId() + ". " + movie.getTitle());
        });

        System.out.print("Movie ID:");
        int movieId = Integer.parseInt(scanner.nextLine());

        System.out.print("Rating(0-10):");
        double rating = Double.parseDouble(scanner.nextLine());

        System.out.print("Comment:");
        String comment = scanner.nextLine();

        reviewService.createReview(new Review(
                movieId,
                user.getId(),
                rating,
                comment));

        System.out.println("Succes. Review added");

    }

    public void ShowReviewByMovie() {
        serviceMovie.findAllMovies().forEach(movie -> {
            System.out.println(movie.getId() + ". " + movie.getTitle());
        });

        System.out.print("Enter movie id");

        int movieId = Integer.parseInt(scanner.nextLine());

        reviewService.getReviewsByMovieId(movieId).forEach((review) -> System.out.println(review));

    }

}
