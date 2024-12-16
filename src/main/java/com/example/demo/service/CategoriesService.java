package com.example.demo.service;

import com.example.demo.model.Categories;
import com.example.demo.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    @Autowired(required=true)
    private CategoriesRepository categoriesRepository;

    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    public Categories find(int id) {
        Optional<Categories> category = categoriesRepository.findById(id);
        return category.orElse(null);
    }

    public boolean save(Categories category) {
        try {
            categoriesRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        if (categoriesRepository.existsById(id)) {
            categoriesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}