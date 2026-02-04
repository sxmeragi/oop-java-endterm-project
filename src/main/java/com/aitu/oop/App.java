package com.aitu.oop;

import com.aitu.oop.controller.MovieController;
import com.aitu.oop.controller.ReviewController;
import com.aitu.oop.controller.UserController;
import com.aitu.oop.repository.MovieRepository;
import com.aitu.oop.repository.ReviewRepository;
import com.aitu.oop.repository.UserRepository;
import com.aitu.oop.service.ReviewService;
import com.aitu.oop.service.ServiceMovie;
import com.aitu.oop.service.UserService;

public class App {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        MovieRepository movieRepository = new MovieRepository();
        ReviewRepository reviewRepository = new ReviewRepository();

        UserService userService = new UserService(userRepository);
        ServiceMovie serviceMovie = new ServiceMovie(movieRepository);
        ReviewService reviewService = new ReviewService(reviewRepository);

        MovieController movieController = new MovieController(serviceMovie);
        ReviewController reviewController = new ReviewController(serviceMovie, reviewService);
        UserController userController = new UserController(userService, movieController, reviewController);

        userController.Start();

    }
}
