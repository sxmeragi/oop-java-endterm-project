package com.aitu.oop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aitu.oop.DatabaseConfig.DatabaseConfig;
import com.aitu.oop.entity.User;

public class UserRepository {

    public void addUser(User user) {
        String sql = """
                    INSERT INTO users(username, email, password, role)
                    VALUES(?,?,?,?)
                """;
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = """
                    SELECT * FROM users
                """;
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);

                ResultSet rs = ps.getResultSet()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")

                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;

    }

    public User findByUsername(String username) {
        String sql = """
                    SELECT * FROM users
                    where username = ?
                """;
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

}
