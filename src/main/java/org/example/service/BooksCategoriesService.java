package org.example.service;

import org.example.model.BooksCategories;
import org.example.repository.BooksCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksCategoriesService {

    @Autowired
    private BooksCategoriesRepository booksCategoriesRepository;

    public List<BooksCategories> findAll() {
        return booksCategoriesRepository.findAll();
    }

    public BooksCategories find(int id) {
        Optional<BooksCategories> booksCategory = booksCategoriesRepository.findById(id);
        return booksCategory.orElse(null);
    }

    public boolean save(BooksCategories booksCategory) {
        try {
            booksCategoriesRepository.save(booksCategory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        if (booksCategoriesRepository.existsById(id)) {
            booksCategoriesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
