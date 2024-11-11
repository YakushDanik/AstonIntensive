package org.example.dao;

import org.example.model.Categories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriesDao implements Dao<Categories, Integer> {
    private final Connection connection;

    public CategoriesDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Categories> find(Integer id) throws SQLException {
        String query = "SELECT * FROM categories WHERE category_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Categories(rs.getInt("category_id"), rs.getString("name")));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Categories> findAll() throws SQLException {
        List<Categories> categoriesList = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                categoriesList.add(new Categories(rs.getInt("category_id"), rs.getString("name")));
            }
        }
        return categoriesList;
    }

    @Override
    public boolean save(Categories category) throws SQLException {
        String query = "INSERT INTO categories (category_id, name) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, category.getCategoryID());
            stmt.setString(2, category.getName());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Categories category) throws SQLException {
        String query = "UPDATE categories SET name = ? WHERE category_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getCategoryID());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Categories category) throws SQLException {
        String query = "DELETE FROM categories WHERE category_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, category.getCategoryID());
            return stmt.executeUpdate() > 0;
        }
    }
}
