package com.herokuapp.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestControllerr {

    @GetMapping("")
    public String helloWorld() {
        return "Hello World! This is TaxiHub :)";
    }
}
