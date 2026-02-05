package com.aitu.oop.controller;

import java.util.Scanner;

import com.aitu.oop.entity.Movie;
import com.aitu.oop.entity.Review;
import com.aitu.oop.entity.User;
import com.aitu.oop.service.ReviewService;
import com.aitu.oop.service.ServiceMovie;
import com.aitu.oop.service.UserService;

public class ReviewController {
    private final ServiceMovie serviceMovie;
    private final ReviewService reviewService;
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public ReviewController(ServiceMovie serviceMovie, ReviewService reviewService, UserService userService) {
        this.serviceMovie = serviceMovie;
        this.userService = userService;
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
        System.err.println("");
        serviceMovie.findAllMovies().forEach(movie -> {
            System.out.println(movie.getId() + ". " + movie.getTitle());
        });

        System.out.print("Enter movie id:");
        System.err.println("");
        int movieId = Integer.parseInt(scanner.nextLine());

        Movie movie = serviceMovie.findMovieById(movieId);
        reviewService.getReviewsByMovieId(movieId).forEach((review) -> 
        System.out.println(
                "\nUsername:" +userService.findById(review.getUserId()).getUserName()+
                "\nMovie:" + movie.getTitle()+
                "\nRating:" + review.getRating()+
                "\nComment:" + review.getComment()


        ));


    }

}
