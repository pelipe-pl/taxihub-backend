package com.herokuapp.backend.auth;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.herokuapp.backend.errors.ErrorCodes.ERROR_403_FORBIDEN;

@RestController()
public class AuthController {

    @GetMapping("denied")
    public void forbiddenError() {
       throw new AccessDeniedException(ERROR_403_FORBIDEN);
    }

}
