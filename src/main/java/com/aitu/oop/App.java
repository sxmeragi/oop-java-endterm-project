// new App.java for factory

package com.aitu.oop;

import com.aitu.oop.controller.MovieController;
import com.aitu.oop.controller.ReviewController;
import com.aitu.oop.controller.UserController;
import com.aitu.oop.factory.RepositoryFactory;
import com.aitu.oop.repository.GenreRepository;
import com.aitu.oop.repository.MovieRepository;
import com.aitu.oop.repository.ReviewRepository;
import com.aitu.oop.repository.UserRepository;
import com.aitu.oop.service.GenreService;
import com.aitu.oop.service.ReviewService;
import com.aitu.oop.service.ServiceMovie;
import com.aitu.oop.service.UserService;

public class App {

    public static void main(String[] args) {

        MovieRepository movieRepository = RepositoryFactory.createMovieRepository();

        ServiceMovie serviceMovie = new ServiceMovie(movieRepository);
        UserRepository userRepository = new UserRepository();
        ReviewRepository reviewRepository = new ReviewRepository();
        GenreRepository genreRepository = new GenreRepository();
        GenreService genreService = new GenreService(genreRepository);

        UserService userService = new UserService(userRepository);
        ReviewService reviewService = new ReviewService(reviewRepository);

        MovieController movieController = new MovieController(serviceMovie, genreService);
        ReviewController reviewController = new ReviewController(serviceMovie, reviewService, userService);
        UserController userController = new UserController(userService, movieController, reviewController);

        userController.Start();

    }
}
