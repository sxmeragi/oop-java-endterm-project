package com.aitu.oop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aitu.oop.DatabaseConfig.DatabaseConfig;
import com.aitu.oop.entity.Review;

public class ReviewRepository {

    public void addReview(Review review) {
        String sql = """
                    INSERT INTO reviews(movie_id, user_id, rating, comment_text)
                    VALUES(?,?,?,?)
                """;
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, review.getMovieId());
            ps.setInt(2, review.getUserId());
            ps.setDouble(3, review.getRating());
            ps.setString(4, review.getComment());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Review> getAllReview() {
        List<Review> reviews = new ArrayList<>();

        String sql = """
                    SELECT * FROM reviews;

                """;
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("rating"),
                        rs.getString("comment_text")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    public List<Review> findReviewByUserId(int userId) {

        List<Review> reviews = new ArrayList<>();

        String sql = """
                    SELECT * From reviews
                    WHERE user_id = ?
                """;
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("rating"),
                        rs.getString("comment_text")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    public List<Review> findReviewByMovieID(int movieId) {

        List<Review> reviews = new ArrayList<>();

        String sql = """
                    SELECT * From reviews
                    WHERE movie_id = ?
                """;
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getDouble("rating"),
                        rs.getString("comment_text")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    public List<String> findReviewWithUsersByMovieId(int movieId) {
        List<String> result = new ArrayList<>();

        String sql = """
                    SELECT u.username, r.rating, r.comment_text
                    FROM reviews As r
                    JOIN users u ON r.user_id = u.id
                    WHERE r.movie_id = ?

                """;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String row = rs.getString("username") + " (" + rs.getDouble("rating") + ") "
                        + rs.getString("comment_text");
                result.add(row);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
