package com.temalab.forum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.temalab.forum.model.Comment;
import com.temalab.forum.repository.CommentRepository;

@RestController
@RequestMapping("/comments")
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Comment> findOne(@PathVariable Long id){
        return commentRepository.findById(id);
    }


    @DeleteMapping("/deleteall")
    public List<Comment> deleteAllComments() {
        commentRepository.deleteAll();
        return commentRepository.findAll();
    }
}
