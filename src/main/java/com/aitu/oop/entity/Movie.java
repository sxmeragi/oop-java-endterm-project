package com.aitu.oop.entity;

public class Movie {
    private int id;
    private String title;
    private int releaseYear;
    private double rating;
    private Genre genre;

    public Movie() {
    }

    public Movie(String title, int rYear, double rating, Genre genre) {
        this.title = title;
        this.releaseYear = rYear;
        this.rating = rating;
        this.genre = genre;
    }

    public Movie(int id, String title, int rYear, double rating, Genre genre) {
        this.id = id;
        this.title = title;
        this.releaseYear = rYear;
        this.rating = rating;
        this.genre = genre;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public double getRating() {
        return this.rating;

    }

    public Genre getGenre() {
        return this.genre;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(int rYear) {
        this.releaseYear = rYear;
    }

    public void setRating(double rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating should be between 0 and 10");
        }
        this.rating = rating;

    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", rating=" + rating +
                ", genre=" + (genre != null ? genre.getName() : "None") +
                '}';
    }

}
