package org.example.dao;

import org.example.model.BooksCategories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BooksCategoriesDao implements Dao<BooksCategories, Integer> {
    private final Connection connection;

    public BooksCategoriesDao(Connection connection) {
        this.connection = connection;
    }


//    @Override
//    public Optional<BooksCategories> find(Integer id) throws SQLException {
//        // Здесь нужно уточнить какой ID используется (bookID или categoryID)
//        return Optional.empty();
//    }

    @Override
    public Optional<BooksCategories> find(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<BooksCategories> findAll() throws SQLException {
        List<BooksCategories> booksCategoriesList = new ArrayList<>();
        String query = "SELECT * FROM bookscategories";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                booksCategoriesList.add(new BooksCategories(rs.getInt("book_id"), rs.getInt("category_id")));
            }
        }
        return booksCategoriesList;
    }

    @Override
    public boolean save(BooksCategories bookCategory) throws SQLException {
        String query = "INSERT INTO books_categories (book_id, category_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookCategory.getBookID());
            stmt.setInt(2, bookCategory.getCategoryID());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(BooksCategories entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(BooksCategories bookCategory) throws SQLException {
        String query = "DELETE FROM books_categories WHERE book_id = ? AND category_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookCategory.getBookID());
            stmt.setInt(2, bookCategory.getCategoryID());
            return stmt.executeUpdate() > 0;
        }
    }
}
