package com.temalab.forum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.temalab.forum.model.Topic;
import com.temalab.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicCotroller {

    @Autowired
    private TopicRepository topicRepository;

    //TODO: CREATE ÉS UPDATE
    
    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAllTopics() {
        topicRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAllTopics(@PathVariable Long id) {
        var del = topicRepository.findById(id);
        del.ifPresent(topic -> topicRepository.delete(topic));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
