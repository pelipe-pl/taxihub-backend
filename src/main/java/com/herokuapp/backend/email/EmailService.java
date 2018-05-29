package com.herokuapp.backend.email;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.herokuapp.backend.config.Keys.EMAIL_URL;

@Service
public class EmailService {
    private RestTemplate restTemplate = new RestTemplate();
    private Environment environment;

    public EmailService(Environment environment) {
        this.environment = environment;
    }

    public void send(Email email) {
        restTemplate.postForObject(environment.getRequiredProperty(EMAIL_URL), email, Object.class);
    }
}