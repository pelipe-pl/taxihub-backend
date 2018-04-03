package com.herokuapp.backend.auth;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("test")
    @PreAuthorize("hasAnyRole('CLIENT','DRIVER','CORPORATION')")
    public String display() {
        return "Hello World test!";
    }
}

