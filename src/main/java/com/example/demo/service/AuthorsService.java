package com.example.demo.service;

import com.example.demo.model.Authors;
import com.example.demo.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorsService {

    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public List<Authors> findAll() {
        return authorsRepository.findAll();
    }

    public Authors find(int id) {
        return authorsRepository.findById(id).orElse(null);
    }

    public boolean save(Authors author) {
        try {
            authorsRepository.save(author);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        if (authorsRepository.existsById(id)) {
            authorsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
