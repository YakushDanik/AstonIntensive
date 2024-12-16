//package org.example.dao;
//
//import org.example.model.BooksCategories;
//import org.junit.jupiter.api.*;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.*;
//
//class BooksCategoriesDaoTest {
//    private Connection connection;
//    private BooksCategoriesDao booksCategoriesDao;
//
//    @BeforeEach
//    void setup() throws SQLException {
//        connection = DataSourceFactory.getConnection();
//        booksCategoriesDao = new BooksCategoriesDao(connection);
//    }
//
//    @AfterEach
//    void tearDown() throws SQLException {
//        connection.prepareStatement("DELETE FROM books_categories").execute();
//        if (connection != null) connection.close();
//    }
//
//    @Test
//    void testSaveBooksCategory() throws SQLException {
//        BooksCategories booksCategory = new BooksCategories(1, 1);
//        boolean result = booksCategoriesDao.save(booksCategory);
//        assertTrue(result);
//
//        Optional<BooksCategories> savedBooksCategory = booksCategoriesDao.findAll().stream()
//                .filter(bc -> bc.getBookID() == 1 && bc.getCategoryID() == 1)
//                .findFirst();
//        assertTrue(savedBooksCategory.isPresent());
//    }
//
//    @Test
//    void testFindAllBooksCategories() throws SQLException {
//        booksCategoriesDao.save(new BooksCategories(2, 2));
//        booksCategoriesDao.save(new BooksCategories(3, 2));
//
//        var allBooksCategories = booksCategoriesDao.findAll();
//        assertFalse(allBooksCategories.isEmpty());
//        assertEquals(2, allBooksCategories.size());
//    }
//
//    @Test
//    void testDeleteBooksCategory() throws SQLException {
//        BooksCategories booksCategory = new BooksCategories(3, 3);
//        booksCategoriesDao.save(booksCategory);
//
//        boolean deleted = booksCategoriesDao.delete(booksCategory);
//        assertTrue(deleted);
//
//        Optional<BooksCategories> deletedBooksCategory = booksCategoriesDao.findAll().stream()
//                .filter(bc -> bc.getBookID() == 3 && bc.getCategoryID() == 2)
//                .findFirst();
//        assertFalse(deletedBooksCategory.isPresent());
//    }
//}
