package org.example.controller;

import org.example.model.Books;
import org.example.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping
    public List<Books> getAllBooks() {
        return booksService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable int id) {
        Books book = booksService.find(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> addBook(@RequestBody Books book) {
        boolean success = booksService.save(book);
        return success ? ResponseEntity.status(201).build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        boolean success = booksService.delete(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
