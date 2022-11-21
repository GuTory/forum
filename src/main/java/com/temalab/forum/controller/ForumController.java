package com.temalab.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityManager;

@Controller
public class ForumController {

    @Autowired
    private EntityManager em;

    @GetMapping("/")
    public RedirectView landingSite(){
        return new RedirectView("/login");
    }

    @GetMapping("/register")
    public String registerSite(Model model){
        return "register";
    }

    @GetMapping("/login")
    public String loginSite(){
        return "login";
    }

}
