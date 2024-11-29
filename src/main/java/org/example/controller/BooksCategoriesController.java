package org.example.controller;

import org.example.model.BooksCategories;
import org.example.service.BooksCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books-categories")
public class BooksCategoriesController {

    @Autowired
    private BooksCategoriesService booksCategoriesService;

    @GetMapping
    public List<BooksCategories> getAllBooksCategories() {
        return booksCategoriesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksCategories> getBooksCategoryById(@PathVariable int id) {
        BooksCategories booksCategory = booksCategoriesService.find(id);
        return booksCategory != null ? ResponseEntity.ok(booksCategory) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> addBooksCategory(@RequestBody BooksCategories booksCategory) {
        boolean success = booksCategoriesService.save(booksCategory);
        return success ? ResponseEntity.status(201).build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooksCategory(@PathVariable int id) {
        boolean success = booksCategoriesService.delete(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
