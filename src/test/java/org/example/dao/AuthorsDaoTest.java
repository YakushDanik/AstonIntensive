//package org.example.dao;
//
//import org.example.dao.AuthorsDao;
//import org.example.model.Authors;
//import org.junit.jupiter.api.*;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AuthorsDaoTest {
//    private Connection connection;
//    private AuthorsDao authorsDao;
//
//    @BeforeEach
//    void setup() throws SQLException {
//        connection = DataSourceFactory.getConnection();
//        authorsDao = new AuthorsDao(connection);
//    }
//
//    @AfterEach
//    void tearDown() throws SQLException {
//        // Очистите таблицу после каждого теста
//        connection.prepareStatement("DELETE FROM authors").execute();
//        if (connection != null) connection.close();
//    }
//
//    @Test
//    void testSaveAuthor() throws SQLException {
//        Authors author = new Authors(1, "Test Author");
//        boolean result = authorsDao.save(author);
//        assertTrue(result);
//
//        Optional<Authors> savedAuthor = authorsDao.find(1);
//        assertTrue(savedAuthor.isPresent());
//        assertEquals("Test Author", savedAuthor.get().getName());
//    }
//
//    @Test
//    void testFindAuthor() throws SQLException {
//        Authors author = new Authors(2, "Sample Author");
//        authorsDao.save(author);
//
//        Optional<Authors> foundAuthor = authorsDao.find(2);
//        assertTrue(foundAuthor.isPresent());
//        assertEquals("Sample Author", foundAuthor.get().getName());
//    }
//
//    @Test
//    void testUpdateAuthor() throws SQLException {
//        Authors author = new Authors(3, "Old Name");
//        authorsDao.save(author);
//
//        author.setName("New Name");
//        boolean updated = authorsDao.update(author);
//        assertTrue(updated);
//
//        Optional<Authors> updatedAuthor = authorsDao.find(3);
//        assertTrue(updatedAuthor.isPresent());
//        assertEquals("New Name", updatedAuthor.get().getName());
//    }
//
//    @Test
//    void testDeleteAuthor() throws SQLException {
//        Authors author = new Authors(4, "Delete Me");
//        authorsDao.save(author);
//
//        boolean deleted = authorsDao.delete(author);
//        assertTrue(deleted);
//
//        Optional<Authors> deletedAuthor = authorsDao.find(4);
//        assertFalse(deletedAuthor.isPresent());
//    }
//}
