//package org.example.dao;
//
//import org.example.dao.BooksDao;
//import org.example.model.Books;
//import org.junit.jupiter.api.*;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BooksDaoTest {
//    private static Connection connection;
//    private static BooksDao booksDao;
//
//    @BeforeAll
//    static void setup() throws SQLException {
//        connection = DataSourceFactory.getConnection();
//        booksDao = new BooksDao(connection);
//    }
//
//    @AfterEach
//    void tearDown() throws SQLException {
//        connection.prepareStatement("DELETE FROM books").execute();
//    }
//
//    @AfterAll
//    static void close() throws SQLException {
//        if (connection != null) connection.close();
//    }
//
//    @Test
//    void testSaveBook() throws SQLException {
//        Books book = new Books(1, "Test Book", 1);
//        boolean result = booksDao.save(book);
//        assertTrue(result);
//
//        Optional<Books> savedBook = booksDao.find(1);
//        assertTrue(savedBook.isPresent());
//        assertEquals("Test Book", savedBook.get().getTitle());
//    }
//
//    @Test
//    void testFindBook() throws SQLException {
//        Books book = new Books(2, "Sample Book", 1);
//        booksDao.save(book);
//
//        Optional<Books> foundBook = booksDao.find(2);
//        assertTrue(foundBook.isPresent());
//        assertEquals("Sample Book", foundBook.get().getTitle());
//    }
//
//    @Test
//    void testUpdateBook() throws SQLException {
//        Books book = new Books(3, "Old Title", 1);
//        booksDao.save(book);
//
//        book.setTitle("New Title");
//        boolean updated = booksDao.update(book);
//        assertTrue(updated);
//
//        Optional<Books> updatedBook = booksDao.find(3);
//        assertTrue(updatedBook.isPresent());
//        assertEquals("New Title", updatedBook.get().getTitle());
//    }
//
//    @Test
//    void testDeleteBook() throws SQLException {
//        Books book = new Books(4, "Delete Me", 1);
//        booksDao.save(book);
//
//        boolean deleted = booksDao.delete(book);
//        assertTrue(deleted);
//
//        Optional<Books> deletedBook = booksDao.find(4);
//        assertFalse(deletedBook.isPresent());
//    }
//}
