package com.herokuapp.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("me")
public class UserController {

    private final FirebaseRegistrationService firebaseRegistrationService;
    private final UserService userService;

    @Autowired
    public UserController(FirebaseRegistrationService firebaseRegistrationService, UserService userService) {
        this.firebaseRegistrationService = firebaseRegistrationService;
        this.userService = userService;
    }

    @GetMapping
    public Authentication me() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PostMapping(value = "/password/reset")
    public void sendPasswordResetToken(@RequestBody String email){
        userService.sendPasswordResetToken(email);
    }

    @PutMapping(value = "/password/reset")
    public void resetPassword(@RequestBody List<String> data) throws ExecutionException, InterruptedException {
        userService.resetPassword(data.get(0), data.get(1), data.get(2));
    }
}