package com.herokuapp.backend.auth;


import com.herokuapp.backend.errors.Error;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController()
public class AuthController {



    @GetMapping("denied")
    public List<Error> accessDeniedErrorMessage() {
        return Collections.singletonList(new Error("User do not have the necessary permissions to access this page"));
    }

}
