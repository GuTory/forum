package com.temalab.forum.controller;

import com.temalab.forum.model.User;
import com.temalab.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityManager;
import java.util.List;


@Controller
public class ForumController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String landingSite(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginSite(){
        return "login";
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
