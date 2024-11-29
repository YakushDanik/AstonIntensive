//package org.example.dao;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public class SessionFactoryProvider {
//    private static SessionFactory sessionFactory;
//
//    static {
//        initializeSessionFactory();
//    }
//
//    private static void initializeSessionFactory() {
//        try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed: " + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null || sessionFactory.isClosed()) {
//            initializeSessionFactory();
//        }
//        return sessionFactory;
//    }
//
//    public static void setSessionFactory(SessionFactory customSessionFactory) {
//        if (sessionFactory != null && !sessionFactory.isClosed()) {
//            sessionFactory.close();
//        }
//        sessionFactory = customSessionFactory;
//    }
//
//    public static void shutdown() {
//        if (sessionFactory != null) {
//            sessionFactory.close();
//        }
//    }
//}
