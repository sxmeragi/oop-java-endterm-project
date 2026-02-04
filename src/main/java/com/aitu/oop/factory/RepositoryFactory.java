package com.aitu.oop.factory;

import com.aitu.oop.repository.MovieRepository;
import com.aitu.oop.repository.UserRepository;
import com.aitu.oop.repository.ReviewRepository;

public class RepositoryFactory {

    public static MovieRepository createMovieRepository() {
        return new MovieRepository();
    }

    public static UserRepository createUserRepository() {
        return new UserRepository();
    }

    public static ReviewRepository createReviewRepository() {
        return new ReviewRepository();
    }
}
