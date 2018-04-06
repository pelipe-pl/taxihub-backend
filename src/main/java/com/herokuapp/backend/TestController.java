package com.herokuapp.backend;

import com.herokuapp.backend.email.Email;
import com.herokuapp.backend.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @Autowired
    private EmailService emailService;


    @GetMapping()
    public String helloWorld() {
        return "Hello World! This is TaxiHub :)";
    }


    @GetMapping("test")
    @PreAuthorize("hasAnyRole('CLIENT','DRIVER','CORPORATION')")
    public String display() {
        return "Hello World test!";
    }

    @GetMapping("email")
    public String emailSent() {
        Email email = new Email();
        email.setTo("friptu.marcela@gmail.com");
        email.setSubject("Test Email");
        email.setContent("Test Email - the email service works");
        email.setProvider("Provider");
        emailService.send(email);
        return "Email was sent";
    }
}
