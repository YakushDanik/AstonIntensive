//package org.example.dao;
//
//import org.example.model.Categories;
//import org.junit.jupiter.api.*;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.*;
//
//class CategoriesDaoTest {
//    private Connection connection;
//    private CategoriesDao categoriesDao;
//
//    @BeforeEach
//    void setup() throws SQLException {
//        connection = DataSourceFactory.getConnection();
//        categoriesDao = new CategoriesDao(connection);
//    }
//
//    @AfterEach
//    void tearDown() throws SQLException {
//        connection.prepareStatement("DELETE FROM categories").execute();
//        if (connection != null) connection.close();
//    }
//
//    @Test
//    void testSaveCategory() throws SQLException {
//        Categories category = new Categories(1, "Test Category");
//        boolean result = categoriesDao.save(category);
//        assertTrue(result);
//
//        Optional<Categories> savedCategory = categoriesDao.find(1);
//        assertTrue(savedCategory.isPresent());
//        assertEquals("Test Category", savedCategory.get().getName());
//    }
//
//    @Test
//    void testFindCategory() throws SQLException {
//        Categories category = new Categories(2, "Sample Category");
//        categoriesDao.save(category);
//
//        Optional<Categories> foundCategory = categoriesDao.find(2);
//        assertTrue(foundCategory.isPresent());
//        assertEquals("Sample Category", foundCategory.get().getName());
//    }
//
//    @Test
//    void testUpdateCategory() throws SQLException {
//        Categories category = new Categories(3, "Old Category Name");
//        categoriesDao.save(category);
//
//        category.setName("Updated Category Name");
//        boolean updated = categoriesDao.update(category);
//        assertTrue(updated);
//
//        Optional<Categories> updatedCategory = categoriesDao.find(3);
//        assertTrue(updatedCategory.isPresent());
//        assertEquals("Updated Category Name", updatedCategory.get().getName());
//    }
//
//    @Test
//    void testDeleteCategory() throws SQLException {
//        Categories category = new Categories(4, "Delete Category");
//        categoriesDao.save(category);
//
//        boolean deleted = categoriesDao.delete(category);
//        assertTrue(deleted);
//
//        Optional<Categories> deletedCategory = categoriesDao.find(4);
//        assertFalse(deletedCategory.isPresent());
//    }
//}
