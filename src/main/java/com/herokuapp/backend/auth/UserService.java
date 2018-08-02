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
        }
    }

    void resetPassword(String token, String newPassword, String newPasswordConfirm) throws ExecutionException, InterruptedException {
        if (!newPassword.equals(newPasswordConfirm))
            throw new IllegalArgumentException("Provided passwords do not match.");
        if (getEmailByToken(token) != null) {
            String email = getEmailByToken(token);
            firebaseRegistrationService.resetPassword(email, newPassword);
            emailService.sendPasswordResetConfirmationEmail(email);
        } else throw new IllegalArgumentException("The token does not match.");
    }

    private String getEmailByToken(String token) {
        if (driverService.getEmailByPasswordResetToken(token) != null)
            return driverService.getEmailByPasswordResetToken(token);
        if (corporationService.getEmailByPasswordResetToken(token) != null)
            return corporationService.getEmailByPasswordResetToken(token);
        if (clientService.getEmailByPasswordResetToken(token) != null)
            return clientService.getEmailByPasswordResetToken(token);
        else return null;
    }
}
