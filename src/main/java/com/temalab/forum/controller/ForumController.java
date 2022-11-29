package com.temalab.forum.controller;

import com.temalab.forum.model.Category;
import com.temalab.forum.model.Comment;
import com.temalab.forum.model.Topic;
import com.temalab.forum.model.User;
import com.temalab.forum.repository.CategoryRepository;
import com.temalab.forum.repository.CommentRepository;
import com.temalab.forum.repository.TopicRepository;
import com.temalab.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class ForumController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;

    //@GetMapping("/")
    //public String landingSite(){
    //    return "Hi stalker";
    //}

    @GetMapping("/")
    public Map<String, Object> currentUser(OAuth2AuthenticationToken token){
        Map<String, Object> principal =  token.getPrincipal().getAttributes();
        System.out.println(principal);
        return principal;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/topics")
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
    
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
