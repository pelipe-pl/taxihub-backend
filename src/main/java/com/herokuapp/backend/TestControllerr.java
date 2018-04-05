package com.herokuapp.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @GetMapping("")
    public String helloWorld() {
        return "Hello World! This is TaxiHub :)";
    }
}
