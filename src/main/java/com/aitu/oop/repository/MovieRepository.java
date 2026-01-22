package com.aitu.oop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aitu.oop.DatabaseConfig.DatabaseConfig;
import com.aitu.oop.entity.Genre;
import com.aitu.oop.entity.Movie;

public class MovieRepository {

    public void addMovie(Movie movie) {
        String sql = """
                    INSERT INTO movies(title, release_year, rating, genre_id)
                    VALUES(?,?,?,?)
                """;
        try (Connection connect = DatabaseConfig.getConnection();
                PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, movie.getTitle());
            ps.setInt(2, movie.getReleaseYear());
            ps.setDouble(3, movie.getRating());
            ps.setInt(4, movie.getGenre().getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Movie> findAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = """
                    SELECT m.id, m.title, m.release_year, m.rating,
                       g.id AS genre_id, g.name AS genre_name
                FROM movies m
                JOIN genres g ON m.genre_id = g.id
                    """;
        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Genre genre = new Genre(
                        rs.getInt("genre_id"),
                        rs.getString("genre_name"));

                Movie movie = new Movie(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        rs.getDouble("rating"),
                        genre);
                movies.add(movie);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;

    }

    public List<Movie> findMoviesByGenre(int genreId) {
        List<Movie> movies = new ArrayList<>();

        String sql = """
                    SELECT m.id, m.title, m.release_year, m.rating,
                           g.id AS genre_id, g.name AS genre_name
                    FROM movies m
                    JOIN genres g ON m.genre_id = g.id
                    WHERE g.id = ?
                """;

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, genreId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Genre genre = new Genre(
                            rs.getInt("genre_id"),
                            rs.getString("genre_name"));

                    Movie movie = new Movie(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("release_year"),
                            rs.getDouble("rating"),
                            genre);

                    movies.add(movie);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }

    public List<Movie> findTopRatedMovies() {
        List<Movie> movies = new ArrayList<>();

        String sql = """
                    SELECT m.id, m.title, m.release_year, m.rating,
                           g.id AS genre_id, g.name AS genre_name
                    FROM movies m
                    JOIN genres g ON m.genre_id = g.id
                    ORDER BY m.rating DESC
                    LIMIT 10
                """;

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Genre genre = new Genre(
                        rs.getInt("genre_id"),
                        rs.getString("genre_name"));

                Movie movie = new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("release_year"),
                        rs.getDouble("rating"),
                        genre);

                movies.add(movie);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }
}
