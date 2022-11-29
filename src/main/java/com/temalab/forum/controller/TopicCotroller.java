package com.temalab.forum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.temalab.forum.model.Topic;
import com.temalab.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicCotroller {

    @Autowired
    private TopicRepository topicRepository;
    
    @GetMapping("/deleteall")
    public List<Topic> deleteAllTopics() {
        topicRepository.deleteAll();
        return topicRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Topic> findOne(@PathVariable Long id){
        return topicRepository.findById(id);
    }

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}
