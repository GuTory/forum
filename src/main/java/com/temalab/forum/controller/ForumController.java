package com.temalab.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.temalab.forum.model.User;
import com.temalab.forum.repository.UserRepository;

import java.util.Map;

@RestController
public class ForumController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public User currentUser(OAuth2AuthenticationToken token) {
        OAuth2User user = 
            token
                .getPrincipal();

        User signedInUser = new User();
        signedInUser.setUserName(
            user.getAttributes().get("name").toString()
            );
        signedInUser.setEmail(
            user.getAttributes().get("email").toString()
            );
        signedInUser.setFirstName(
            user.getAttributes().get("given_name").toString()
        );
        signedInUser.setLastName(
            user.getAttributes().get("family_name").toString()
        );
        signedInUser.setPassword(
            null
        );
        
        return loginUser(signedInUser);

        // TODO: tokenben kapott user elmentése ha még nincs benne a User-ek között
    }

    private User loginUser(User user){
        for(User u: userRepository.findAll()){
            if(u.equals(user)){
                return u;
            }
        }
        return userRepository.save(user);
    }
    
    // TODO: CRUD művelet endpointok minden elemen
    // TODO: törlések esetén a constraintek betartása: vagy törlöm a constrainteket vagy a kaszkádolást módosítom
}
