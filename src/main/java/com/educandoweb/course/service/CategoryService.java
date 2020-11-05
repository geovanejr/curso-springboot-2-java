package com.educandoweb.course.service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findall() {

        return categoryRepository.findAll();
    }

    public Category findById(Long id) {

        Optional<Category> category = categoryRepository.findById(id);

        return category.get();
    }

    public Category insert(Category category) {

        return categoryRepository.save(category);
    }
}
