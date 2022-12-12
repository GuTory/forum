package com.temalab.forum.controller;

import java.util.List;
import java.util.Optional;

import com.temalab.forum.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.temalab.forum.model.Category;
import com.temalab.forum.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/new")
    public ResponseEntity<String> createCategory(@RequestBody String name,
                                                 @RequestBody Long parentCategoryId) {
        categoryService.createEntity(name, parentCategoryId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> udpateCategory(@PathVariable Long id,
                                                 @RequestBody String name,
                                                 @RequestBody Long parentCategoryId) {
        categoryService.updateEntity(id, name, parentCategoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> findOne(@PathVariable Long id) {
        return categoryService.findById(id);
    }


    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAllCategories() {
        categoryService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        categoryService.deleteWithCascade(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
