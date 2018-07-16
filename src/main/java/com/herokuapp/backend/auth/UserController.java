package com.herokuapp.backend.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("me")
public class UserController {

    @GetMapping
    public Authentication me() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}