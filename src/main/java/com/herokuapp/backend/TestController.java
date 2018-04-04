package com.herokuapp.backend;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@RestController
public class TestController {


    @GetMapping()
    public String helloWorld()  {
        if (1==1) throw new HttpMessageNotReadableException("Something");
        return "Hello World! This is TaxiHub :)";
    }


    @GetMapping("test")
    @PreAuthorize("hasAnyRole('CLIENT','DRIVER','CORPORATION')")
    public String display() {
        return "Hello World test!";
    }
}
