package com.example.demo.controller;

import com.example.demo.model.Authors;
import com.example.demo.service.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired(required=true)
    private AuthorsService authorsService;

    @GetMapping
    public List<Authors> getAllAuthors() {
        return authorsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Authors> getAuthorById(@PathVariable int id) {
        Authors author = authorsService.find(id);
        return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> addAuthor(@RequestBody Authors author) {
        boolean success = authorsService.save(author);
        return success ? ResponseEntity.status(201).build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        boolean success = authorsService.delete(id);
        return success ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
