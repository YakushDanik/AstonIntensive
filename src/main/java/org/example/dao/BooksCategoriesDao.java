package org.example.dao;

import org.example.model.BooksCategories;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BooksCategoriesDao {

    public BooksCategories find(int id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(BooksCategories.class, id);
        }
    }

    public List<BooksCategories> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.createQuery("FROM BooksCategories", BooksCategories.class).list();
        }
    }

    public boolean save(BooksCategories booksCategories) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(booksCategories);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(int id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            BooksCategories booksCategory = session.get(BooksCategories.class, id);
            if (booksCategory != null) {
                session.remove(booksCategory);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
