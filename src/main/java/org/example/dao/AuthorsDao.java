package org.example.dao;

import org.example.model.Authors;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class AuthorsDao {

    public List<Authors> findAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.createQuery("FROM Authors", Authors.class).list();
        }
    }

    public Authors find(Integer id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(Authors.class, id);
        }
    }

    public boolean save(Authors author) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(author);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Integer id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Authors author = session.get(Authors.class, id);
            if (author != null) {
                session.delete(author);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}