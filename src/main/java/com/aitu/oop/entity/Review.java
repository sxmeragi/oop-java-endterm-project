package com.aitu.oop.entity;

public class Review {
    private int id;
    private int movieId;
    private int userId;
    private double rating;
    private String commentText;

    public Review() {

    }

    public Review(int movieId, int userId, double rating, String commentText) {
        this.movieId = movieId;
        this.userId = userId;
        this.rating = rating;
        this.commentText = commentText;
    }

    public Review(int id, int movieId, int userId, double rating, String commentText) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.rating = rating;
        this.commentText = commentText;
    }

    public int getId() {
        return this.id;
    }

    public int getMovieId() {
        return this.movieId;
    }

    public int getUserId() {
        return this.userId;
    }

    public double getRating() {
        return this.rating;
    }

    public String getComment() {
        return this.commentText;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.commentText = comment;
    }

    @Override
    public String toString() {
        return "Review{id=" + id + ", movieId=" + movieId + ", userId=" + userId +
                ", rating=" + rating + ", comment='" + commentText + "'}";
    }
}
