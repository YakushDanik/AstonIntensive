package com.example.demo.service;

import com.example.demo.model.Books;
import com.example.demo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired(required=true)
    private BooksRepository booksRepository;

    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    public Books find(int id) {
        Optional<Books> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    public boolean save(Books book) {
        try {
            booksRepository.save(book);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        if (booksRepository.existsById(id)) {
            booksRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
