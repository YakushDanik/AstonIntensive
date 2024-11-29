package org.example.dao;

import org.example.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseDaoTest {
    protected SessionFactory sessionFactory;

    @BeforeEach
    public void setUp() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Authors.class)
                .addAnnotatedClass(Books.class)
                .addAnnotatedClass(BooksCategories.class)
                .addAnnotatedClass(Categories.class)
                .buildSessionFactory();
        SessionFactoryProvider.setSessionFactory(sessionFactory);
    }

    @AfterEach
    public void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
