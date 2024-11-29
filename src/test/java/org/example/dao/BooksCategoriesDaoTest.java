package org.example.dao;

import org.example.model.Authors;
import org.example.model.Books;
import org.example.model.BooksCategories;
import org.example.model.Categories;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BooksCategoriesDaoTest extends BaseDaoTest {
    private final AuthorsDao authorsDao = new AuthorsDao();
    private final BooksDao booksDao = new BooksDao();
    private final CategoriesDao categoriesDao = new CategoriesDao();
    private final BooksCategoriesDao booksCategoriesDao = new BooksCategoriesDao();

    @Test
    void testSaveAndFind() {
        Authors author = new Authors(0, "Author");
        authorsDao.save(author);

        Books book = new Books("Book", author);
        booksDao.save(book);

        Categories category = new Categories("Category");
        categoriesDao.save(category);

        BooksCategories booksCategory = new BooksCategories(book, category);
        assertTrue(booksCategoriesDao.save(booksCategory));

        BooksCategories fetched = booksCategoriesDao.find(booksCategory.getId());
        assertNotNull(fetched);
        assertEquals(book.getBookID(), fetched.getBook().getBookID());
    }

}
