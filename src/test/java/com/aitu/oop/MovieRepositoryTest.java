package com.aitu.oop;

import com.aitu.oop.repository.MovieRepository;;

public class MovieRepositoryTest {
    public static void main(String[] args) {
        MovieRepository repo = new MovieRepository();
        System.out.println(repo.findAllMovies());
    }
}
