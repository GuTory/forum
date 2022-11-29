package com.temalab.forum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.temalab.forum.model.User;
import com.temalab.forum.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findOne(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        Optional<User> deleteWannabe = userRepository.findById(id);
        if(deleteWannabe.isPresent())
            userRepository.delete(deleteWannabe.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/deleteall")
    public List<User> deleteall() {
        userRepository.deleteAll();
        return userRepository.findAll();
    }
}
