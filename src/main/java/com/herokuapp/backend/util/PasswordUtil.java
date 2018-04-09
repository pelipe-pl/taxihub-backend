package com.herokuapp.backend.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    private static int workload = 12;

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        return(BCrypt.hashpw(password_plaintext, salt));
    }
}
