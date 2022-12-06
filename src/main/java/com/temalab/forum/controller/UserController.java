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
    public Optional<User> findOne(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createUser(@RequestBody String username,
                                             @RequestBody String password,
                                             @RequestBody String email,
                                             @RequestBody String firstName,
                                             @RequestBody String lastname
    ) {
        User newUser = new User();
        newUser.setUserName(username);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastname);
        newUser.setEmail(email);
        userRepository.save(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody String username,
                                             @RequestBody String password,
                                             @RequestBody String email,
                                             @RequestBody String firstName,
                                             @RequestBody String lastname) {
        Optional<User> opt = userRepository.findById(id);
        if(opt.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var u = opt.get();
        u.setUserName(username);
        u.setPassword(password);
        u.setFirstName(firstName);
        u.setLastName(lastname);
        u.setEmail(email);
        userRepository.save(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activateUser(@PathVariable Long id) {
        Optional<User> activate = userRepository.findById(id);
        activate.ifPresent(user -> user.setActive(true));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * törléskor nem törlődnek ténylegesen a felhasználók
     *
     * @return
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> deleteWannabe = userRepository.findById(id);
        deleteWannabe.ifPresent(user -> user.setActive(false));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * törléskor nem törlődnek ténylegesen a felhasználók
     *
     * @return
     */
    @PutMapping("/deleteall")
    public ResponseEntity<String> deleteall() {
        for (User u : userRepository.findAll()) {
            u.setActive(false);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
