package org.example.dao;

import org.example.model.Books;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BooksDao implements Dao<Books, Integer> {
    private final Connection connection;

    public BooksDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Books> find(Integer id) throws SQLException {
        String query = "SELECT * FROM books WHERE book_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Books(rs.getInt("book_id"), rs.getString("title"), rs.getInt("author_id")));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Books> findAll() throws SQLException {
        List<Books> booksList = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                booksList.add(new Books(rs.getInt("book_id"), rs.getString("title"), rs.getInt("author_id")));
            }
        }
        return booksList;
    }

    @Override
    public boolean save(Books book) throws SQLException {
        String query = "INSERT INTO books (book_id, title, author_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, book.getBookID());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getAuthorID());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Books book) throws SQLException {
        String query = "UPDATE books SET title = ?, author_id = ? WHERE book_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthorID());
            stmt.setInt(3, book.getBookID());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Books book) throws SQLException {
        String query = "DELETE FROM books WHERE book_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, book.getBookID());
            return stmt.executeUpdate() > 0;
        }
    }
}
