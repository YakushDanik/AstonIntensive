package org.example.dao;

import org.example.model.Authors;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorsDao implements Dao<Authors, Integer> {
    private final Connection connection;

    public AuthorsDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Authors> find(Integer id) throws SQLException {
        String query = "SELECT * FROM authors WHERE author_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Authors(rs.getInt("author_id"), rs.getString("name")));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Authors> findAll() throws SQLException {
        List<Authors> authorsList = new ArrayList<>();
        String query = "SELECT * FROM authors";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                authorsList.add(new Authors(rs.getInt("author_id"), rs.getString("name")));
            }
        }
        return authorsList;
    }

    @Override
    public boolean save(Authors author) throws SQLException {
        String query = "INSERT INTO authors (author_id, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, author.getAuthorID());
            stmt.setString(2, author.getName());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Authors author) throws SQLException {
        String query = "UPDATE authors SET name = ? WHERE author_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, author.getName());
            stmt.setInt(2, author.getAuthorID());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Authors author) throws SQLException {
        String query = "DELETE FROM authors WHERE author_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, author.getAuthorID());
            return stmt.executeUpdate() > 0;
        }
    }
}
