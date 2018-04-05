package com.herokuapp.backend.auth;


import com.herokuapp.backend.errors.Error;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController()
public class AuthController {

    @GetMapping("denied")
    public void forbidenError() {
       throw new AccessDeniedException("Access forbiden");
    }

}
