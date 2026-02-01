package com.aitu.oop;

import com.aitu.oop.controller.MovieController;
import com.aitu.oop.repository.MovieRepository;
import com.aitu.oop.service.ServiceMovie;

public class App {
    public static void main(String[] args) {

        MovieRepository movieRepository = new MovieRepository();
        ServiceMovie serviceMovie = new ServiceMovie(movieRepository);
        MovieController movieController = new MovieController(serviceMovie);
        movieController.start();

    }
}
