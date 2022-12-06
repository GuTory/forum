package com.temalab.forum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.temalab.forum.model.Category;
import com.temalab.forum.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> findOne(@PathVariable Long id){
        return categoryRepository.findById(id);
    }


    @DeleteMapping("/deleteall")
    public List<Category> deleteAllCategories() {
        categoryRepository.deleteAll();
        return categoryRepository.findAll();
    }
}
