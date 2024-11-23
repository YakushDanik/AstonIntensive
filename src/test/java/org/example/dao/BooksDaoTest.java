package org.example.dao;

import org.example.model.Authors;
import org.example.model.Books;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BooksDaoTest extends BaseDaoTest {
    private final AuthorsDao authorsDao = new AuthorsDao();
    private final BooksDao booksDao = new BooksDao();

    @Test
    void testSaveAndFind() {
        Authors author = new Authors(0, "Author");
        authorsDao.save(author);

        Books book = new Books("Test Book", author);
        assertTrue(booksDao.save(book));

        Books fetched = booksDao.find(book.getBookID());
        assertNotNull(fetched);
        assertEquals("Test Book", fetched.getTitle());
    }


    @Test
    void testDelete() {
        Authors author = new Authors(0, "Author");
        authorsDao.save(author);

        Books book = new Books("Delete Book", author);
        booksDao.save(book);

        assertTrue(booksDao.delete(book.getBookID()));
        assertNull(booksDao.find(book.getBookID()));
    }
}
