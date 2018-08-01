package com.herokuapp.backend.auth;

import com.herokuapp.backend.client.ClientServiceFacade;
import com.herokuapp.backend.corporation.CorporationServiceFacade;
import com.herokuapp.backend.driver.DriverServiceFacade;
import com.herokuapp.backend.email.EmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private final ClientServiceFacade clientService;
    private final CorporationServiceFacade corporationService;
    private final DriverServiceFacade driverService;
    private final EmailService emailService;
    private final FirebaseRegistrationService firebaseRegistrationService;

    public UserService(ClientServiceFacade clientService, CorporationServiceFacade corporationService, DriverServiceFacade driverService, EmailService emailService, FirebaseRegistrationService firebaseRegistrationService) {
        this.clientService = clientService;
        this.corporationService = corporationService;
        this.driverService = driverService;
        this.emailService = emailService;
        this.firebaseRegistrationService = firebaseRegistrationService;
    }

    void sendPasswordResetToken(String email) {
        if (clientService.existsByEmail(email)) {
            String token = RandomStringUtils.randomAlphabetic(20);
            clientService.setPasswordResetToken(email, token);
            emailService.sendPasswordResetEmail(email, token);
        }
        if (corporationService.existsByEmail(email)) {
            String token = RandomStringUtils.randomAlphabetic(20);
            corporationService.setPasswordResetToken(email, token);
            emailService.sendPasswordResetEmail(email, token);
        }
        if (driverService.existByEmail(email)) {
            String token = RandomStringUtils.randomAlphabetic(20);
            driverService.setPasswordResetToken(email, token);
            emailService.sendPasswordResetEmail(email, token);
        } else throw new IllegalArgumentException("Could not find user with this e-mail.");
    }

    void resetPassword(String token, String newPassword, String newPasswordConfirm) throws ExecutionException, InterruptedException {

        //TODO write all the checking

        firebaseRegistrationService.resetPassword(token, newPassword);
    }
}
