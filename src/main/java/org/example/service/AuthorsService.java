package org.example.service;

import org.example.model.Authors;
import org.example.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    public List<Authors> findAll() {
        return authorsRepository.findAll();
    }

    public Authors find(int id) {
        Optional<Authors> author = authorsRepository.findById(id);
        return author.orElse(null);
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
