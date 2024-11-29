//package org.example.dao;
//
//import org.example.model.Books;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.List;
//
//public class BooksDao {
//
//    public Books find(Integer id) {
//        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
//            return session.get(Books.class, id);
//        }
//    }
//
//    public List<Books> findAll() {
//        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Books", Books.class).list();
//        }
//    }
//
//    public boolean save(Books book) {
//        Transaction transaction = null;
//        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(book);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    public boolean delete(Integer id) {
//        Transaction transaction = null;
//        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Books book = session.get(Books.class, id);
//            if (book != null) {
//                session.delete(book);
//                transaction.commit();
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//            return false;
//        }
//    }
//}