package com.aitu.oop.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aitu.oop.DatabaseConfig.DatabaseConfig;
import com.aitu.oop.entity.Genre;

public class GenreRepository {
    
    public Genre findByName(String name) {
        String sql = "SELECT id, name FROM genres WHERE name = ?";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Genre(
                    rs.getInt("id"),
                    rs.getString("name")
                );
            }

            throw new RuntimeException("Genre not found: " + name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
}

}