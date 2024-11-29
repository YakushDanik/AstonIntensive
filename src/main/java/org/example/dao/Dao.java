//package org.example.dao;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//public interface Dao<T, ID> {
//    Optional<T> find(ID id) throws SQLException;
//    List<T> findAll() throws SQLException;
//    boolean save(T entity) throws SQLException;
//    boolean update(T entity) throws SQLException;
//    boolean delete(T entity) throws SQLException;
//}