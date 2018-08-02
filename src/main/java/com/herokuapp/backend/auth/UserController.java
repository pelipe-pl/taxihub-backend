package com.herokuapp.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public void sendPasswordResetToken(@RequestBody Email email) {
        userService.sendPasswordResetToken(email.getAddress());
    }

    @PutMapping(value = "/password/reset")
    public void resetPassword(@RequestBody PasswordResetData data) throws ExecutionException, InterruptedException {
        userService.resetPassword(data.getToken(), data.getNewPassword(), data.getNewPasswordConfirm());
    }

    private static class Email {
        private String address;

        private String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    private static class PasswordResetData {
        private String token;
        private String newPassword;
        private String newPasswordConfirm;

        private String getToken() {
            return token;
        }

        private String getNewPassword() {
            return newPassword;
        }

        private String getNewPasswordConfirm() {
            return newPasswordConfirm;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public void setNewPasswordConfirm(String newPasswordConfirm) {
            this.newPasswordConfirm = newPasswordConfirm;
        }
    }
}