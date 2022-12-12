package com.temalab.forum.services;

import com.temalab.forum.model.Category;
import com.temalab.forum.model.Topic;
import com.temalab.forum.repository.CategoryRepository;
import com.temalab.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TopicRepository topicRepository;

    public void deleteWithCascade(Long categoryID) {
        var c = categoryRepository.findById(categoryID);
        if (c.isEmpty()) return;
        var category = c.get();
        var topics = topicRepository.findAll();
        for (Topic t : topics) {
            t.removeCategory(category);
        }
        categoryRepository.deleteById(categoryID);
    }

    public void deleteAll() {
        for (var t : categoryRepository.findAll()) {
            deleteWithCascade(t.getId());
        }
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void createEntity(String name, Long parentCategoryId) {
        var parent = categoryRepository.findById(parentCategoryId);
        Category newCategory = new Category();
        if (parent.isPresent()) newCategory.setParentCategory(parent.get());
        newCategory.setName(name);
        categoryRepository.save(newCategory);
    }

    public void updateEntity(Long id, String name, Long parentCategoryId) {
        var parent = categoryRepository.findById(parentCategoryId);
        Optional<Category> updateEntity = categoryRepository.findById(id);
        if (parent.isEmpty() || updateEntity.isEmpty()) return;

        updateEntity.get().setParentCategory(parent.get());
        updateEntity.get().setName(name);
        categoryRepository.save(updateEntity.get());
    }
}
