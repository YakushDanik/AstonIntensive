package org.example.dao;

import org.example.model.Authors;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorsDaoTest extends BaseDaoTest {
    private final AuthorsDao authorsDao = new AuthorsDao();

    @Test
    void testSaveAndFind() {
        Authors author = new Authors();
        author.setName("John Doe");
        assertTrue(authorsDao.save(author));

        Authors fetched = authorsDao.find(author.getAuthorID());
        assertNotNull(fetched);
        assertEquals("John Doe", fetched.getName());
    }


    @Test
    void testDelete() {
        Authors author = new Authors(0, "Delete Me");
        authorsDao.save(author);

        assertTrue(authorsDao.delete(author.getAuthorID()));
        assertNull(authorsDao.find(author.getAuthorID()));
    }
}
