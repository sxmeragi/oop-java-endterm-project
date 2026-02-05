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

        checkReview(review);
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

    private void checkReview(Review review) {
        if (review.getComment() == null) {
            throw new IllegalArgumentException("Comment should not be empty");
        }

        if (review.getRating() < 0 || review.getRating() > 10) {
            throw new IllegalArgumentException("Rating should be between 0 or 10");
        }
    }

}
