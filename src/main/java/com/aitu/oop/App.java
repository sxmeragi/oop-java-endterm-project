// new App.java for factory

package com.aitu.oop;

import com.aitu.oop.factory.RepositoryFactory;
import com.aitu.oop.repository.MovieRepository;
import com.aitu.oop.service.ServiceMovie;
import com.aitu.oop.controller.MovieController;

public class App {

    public static void main(String[] args) {

        MovieRepository movieRepository = RepositoryFactory.createMovieRepository();

        ServiceMovie serviceMovie = new ServiceMovie(movieRepository);
        MovieController movieController = new MovieController(serviceMovie);

        movieController.start();
    }
}
