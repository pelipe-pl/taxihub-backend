package com.herokuapp.backend;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping()
    public String helloWorld() {
        if (1==1) throw new IllegalArgumentException("some error");
        return "Hello World! This is TaxiHub :)";
    }


    @GetMapping("test")
    @PreAuthorize("hasAnyRole('CLIENT','DRIVER','CORPORATION')")
    public String display() {
        return "Hello World test!";
    }
}
