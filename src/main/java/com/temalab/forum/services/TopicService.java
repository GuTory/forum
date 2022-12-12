package com.temalab.forum.services;

import com.temalab.forum.model.Topic;
import com.temalab.forum.model.User;
import com.temalab.forum.repository.CategoryRepository;
import com.temalab.forum.repository.TopicRepository;
import com.temalab.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public void deleteAll() {
        topicRepository.deleteAll();
    }

    public void deleteById(Long id) {
        var del = topicRepository.findById(id);
        del.ifPresent(topic -> topicRepository.delete(topic));
    }

    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public void createEntity(String name, Long issuerId) {
        Optional<User> user = userRepository.findById(issuerId);
        Topic newTopic = new Topic();
        newTopic.setName(name);
        newTopic.setIssuer(user.get());
        newTopic.setCategories(new ArrayList<>());
        topicRepository.save(newTopic);
    }

    public void updateEntity(Long id, String name) {
        var t = topicRepository.findById(id);
        if (t.isEmpty()) return;
        t.get().setName(name);
        topicRepository.save(t.get());
    }

    public void addCategory(Long id, Long categoryId) {
        var t = topicRepository.findById(id);
        var c = categoryRepository.findById(categoryId);
        if (t.isEmpty() || c.isEmpty()) return;

        t.get().addCategory(c.get());
    }

    public void removeCategory(Long id, Long categoryId) {
        var t = topicRepository.findById(id);
        var c = categoryRepository.findById(categoryId);
        if (t.isEmpty() || c.isEmpty()) return;

        t.get().removeCategory(c.get());
    }
}
