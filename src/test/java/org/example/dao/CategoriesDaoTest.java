package org.example.dao;

import org.example.model.Categories;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriesDaoTest extends BaseDaoTest {
    private final CategoriesDao categoriesDao = new CategoriesDao();

    @Test
    void testSaveAndFind() {
        Categories category = new Categories("Test Category");
        assertTrue(categoriesDao.save(category));

        Categories fetched = categoriesDao.find(category.getCategoryID());
        assertNotNull(fetched);
        assertEquals("Test Category", fetched.getName());
    }


    @Test
    void testDelete() {
        Categories category = new Categories("Delete Category");
        categoriesDao.save(category);

        assertTrue(categoriesDao.delete(category.getCategoryID()));
        assertNull(categoriesDao.find(category.getCategoryID()));
    }
}
