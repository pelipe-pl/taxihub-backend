package com.herokuapp.backend.email;

import com.herokuapp.backend.client.ClientEntity;
import com.herokuapp.backend.corporation.CorporationEntity;
import com.herokuapp.backend.driver.DriverEntity;
import com.herokuapp.backend.order.OrderEntity;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.herokuapp.backend.config.Keys.*;

@Service
public class EmailService {
    private static final String CONFIRMATION_CONTENT = "To confirm your email address, please click: \n";
    private static final String PASSWORD_RESET__CONFIRMATION_CONTENT = "Your password for TaxiHub has been changed.";
    private static final String PASSWORD_RESET_CONTENT = "You have requested for new password. Following link is valid for 24 hours. " +
            "To reset your password, please click: \n";
    private RestTemplate restTemplate = new RestTemplate();
    private Environment environment;

    public EmailService(Environment environment) {
        this.environment = environment;
    }

    public void send(Email email) {
        restTemplate.postForObject(environment.getRequiredProperty(EMAIL_URL), email, Object.class);
    }

    public void sendDriverConfirmationEmail(String address, String token) {
        String content = CONFIRMATION_CONTENT + "<a href='" + environment.getRequiredProperty(FRONT_URL) + token + "'>here</a>";
        Email email = new Email(address, "Registration Confirmation", content);
        send(email);
    }

    public void sendWelcomeEmail(ClientEntity clientEntity) {
        String content = "Welcome to TaxiHub, " + clientEntity.getName()
                + " ! \n" + "You have successfully registered. You can make your first order now. "
                + "Click" + " <a href='" + environment.getRequiredProperty(FRONT_URL_HOMEPAGE)
                + "'>here</a> to log in and start!";
        Email email = new Email(clientEntity.getEmail(), "Welcome to TaxiHub!", content);
        send(email);
    }

    public void sendWelcomeEmail(CorporationEntity corporationEntity) {
        String content = "Welcome to TaxiHub, " + corporationEntity.getName()
                + " ! \n" + "You have successfully registered. You can start adding your drivers now. "
                + "Click" + " <a href='" + environment.getRequiredProperty(FRONT_URL_HOMEPAGE)
                + "'>here</a> to log in and start!";
        Email email = new Email(corporationEntity.getEmail(), "Welcome to TaxiHub!", content);
        send(email);
    }

    public void sendWelcomeEmail(DriverEntity driverEntity) {
        String content = "Welcome to TaxiHub, " + driverEntity.getName()
                + " ! \n" + "You have successfully registered. You can start taking orders now. "
                + "Click" + " <a href='" + environment.getRequiredProperty(FRONT_URL_HOMEPAGE)
                + "'>here</a> to log in and start!";
        Email email = new Email(driverEntity.getEmail(), "Welcome to TaxiHub!", content);
        send(email);
    }

    public void sendCloseOrderEmail(OrderEntity order) {
        Email email = new Email();
        email.setTo(order.getClient().getEmail());
        email.setSubject("Thank you for a ride with TaxiHub!");
        String content = "Hello, thank you for the ride! Your trip is finished! See you next time! :)" +
                "Your ride started at: " +
                order.getStartTime() +
                " and finished at: " +
                order.getEndTime();
        email.setContent(content);
        send(email);
    }

    public void sendPasswordResetEmail(String address, String token) {
        String content = PASSWORD_RESET_CONTENT + "<a href='" + environment.getRequiredProperty(FRONT_PASSWORD_RESET_URL) + token + "'>here</a>";
        Email email = new Email(address, "TaxiHub Password Recovery", content);
        send(email);
    }

    public void sendPasswordResetConfirmationEmail(String address) {
        String content = PASSWORD_RESET__CONFIRMATION_CONTENT;
        Email email = new Email(address, "TaxiHub password has been changed", content);
        send(email);
    }
}