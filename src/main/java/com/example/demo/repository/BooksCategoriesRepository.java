package com.example.demo.repository;

import com.example.demo.model.BooksCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksCategoriesRepository extends JpaRepository<BooksCategories, Integer> {
}
