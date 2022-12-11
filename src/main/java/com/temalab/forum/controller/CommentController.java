package com.temalab.forum.controller;

import java.util.List;
import java.util.Optional;

import com.temalab.forum.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.temalab.forum.model.Comment;
import com.temalab.forum.repository.CommentRepository;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/new")
    public ResponseEntity<String> createEntity(@RequestBody String response,
                                               @RequestBody Long topicId,
                                               @RequestBody Long responseTo,
                                               @RequestBody Long responseComment,
                                               @RequestBody Long respondent) {
        commentService.createEntity(response,
                topicId,
                responseTo,
                responseComment,
                respondent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEntity(@PathVariable Long id,
                                               @RequestBody String response,
                                               @RequestBody Long topicId,
                                               @RequestBody Long responseTo,
                                               @RequestBody Long responseComment,
                                               @RequestBody Long respondent) {
        commentService.updateEntity(id,
                response,
                topicId,
                responseTo,
                responseComment,
                respondent);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public Optional<Comment> findOne(@PathVariable Long id) {
        return commentService.findById(id);
    }


    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAllComments() {
        commentService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable Long id){
        commentService.deleteEntity(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Comment> getAllComments() {
        return commentService.findAll();
    }
}
