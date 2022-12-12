package com.temalab.forum.controller;

import java.util.List;
import java.util.Optional;

import com.temalab.forum.services.TopicService;
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
    private TopicService topicService;

    @PostMapping("/new")
    public ResponseEntity<String> createTopic(@RequestBody String name,
                                              @RequestBody Long issuerId) {
        topicService.createEntity(name, issuerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> udpateCategory(@PathVariable Long id,
                                                 @RequestBody String name) {
        topicService.updateEntity(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/addcategory/{id}/{cid}")
    public ResponseEntity<String> addCategory(@PathVariable Long id,
                                              @PathVariable Long cid) {
        topicService.addCategory(id, cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/removecategory/{id}/{cid}")
    public ResponseEntity<String> removeCategory(@PathVariable Long id,
                                              @PathVariable Long cid) {
        topicService.removeCategory(id, cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAllTopics() {
        topicService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAllTopics(@PathVariable Long id) {
        topicService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public Optional<Topic> findOne(@PathVariable Long id) {
        return topicService.findById(id);
    }

    @GetMapping("/")
    public List<Topic> getAllTopics() {
        return topicService.findAll();
    }
}
