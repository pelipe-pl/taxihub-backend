package com.herokuapp.backend.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseRegistrationService {

    public void register(String email, String password) throws ExecutionException, InterruptedException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password);
        FirebaseAuth.getInstance().createUserAsync(request).get();
    }
}