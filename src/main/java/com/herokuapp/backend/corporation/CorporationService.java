package com.herokuapp.backend.corporation;

import com.herokuapp.backend.driver.DriverDto;
import com.herokuapp.backend.driver.DriverEntity;
import com.herokuapp.backend.email.Email;
import com.herokuapp.backend.email.EmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.herokuapp.backend.config.Keys.FRONT_URL;

@Service
public class CorporationService {

    public static final String CONFIRMATION_CONTENT = "To confirm your email address, please click the link below: \n";
    private final CorporationRepository corpRepository;
    private final EmailService emailService;
    private Environment environment;

    public CorporationService( CorporationRepository corpRepository, EmailService emailService, Environment environment) {
        this.corpRepository = corpRepository;
        this.emailService = emailService;
        this.environment = environment;
    }

    public DriverDto createDriver(DriverDto driver, Long corporationId) {
        final DriverEntity entity = new DriverEntity();
        entity.setName(driver.getName());
        entity.setSurname(driver.getSurname());
        entity.setEmail(driver.getEmail());
        entity.setCorporationId(corporationId);
        entity.setToken(RandomStringUtils.randomAlphabetic(20));
        sendConfirmationEmail(driver.getEmail(), entity.getToken());
        return driver;
    }

    private void sendConfirmationEmail(String address, String token) {
        String content = CONFIRMATION_CONTENT + environment.getRequiredProperty(FRONT_URL) + token;
        Email email = new Email(address, "Registration Confirmation", content);
        emailService.send(email);
    }

    public CorporationDto createCorporation(CorporationDto corporation) {
        final CorporationEntity entity = new CorporationEntity();
        entity.setName(corporation.getName());
        entity.setEmail(corporation.getEmail());
        corpRepository.save(entity);
        return corporation;
    }
}