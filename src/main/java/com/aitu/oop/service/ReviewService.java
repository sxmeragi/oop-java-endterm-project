package com.aitu.oop.service;

import java.util.List;

import com.aitu.oop.entity.Review;
import com.aitu.oop.repository.ReviewRepository;

public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void createReview(Review review) {

        reviewRepository.addReview(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.getAllReview();
    }

    public List<Review> getReviewsByMovieId(int movieId) {
        return reviewRepository.findReviewByMovieID(movieId);
    }

    public List<Review> getReviewsByUsersId(int userId) {
        return reviewRepository.findReviewByUserId(userId);
    }

}
