package com.temalab.forum.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class ForumController {

    @GetMapping("/")
    public Map<String, Object> currentUser(OAuth2AuthenticationToken token) {
        Map<String, Object> principal = 
            token
                .getPrincipal()
                .getAttributes();
        return principal;

        // TODO: tokenben kapott user elmentése ha még nincs benne a User-ek között
    }
    
    // TODO: CRUD művelet endpointok minden elemen
    // TODO: törlések esetén a constraintek betartása: vagy törlöm a constrainteket vagy a kaszkádolást módosítom
}
