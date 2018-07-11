package com.herokuapp.backend.auth;

import com.herokuapp.backend.client.ClientRepository;
import com.herokuapp.backend.corporation.CorporationRepository;
import com.herokuapp.backend.driver.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationChecker {
    private final ClientRepository clientRepository;
    private final CorporationRepository corpRepository;
    private final DriverRepository driverRepository;

    public UserRegistrationChecker(ClientRepository clientRepository, CorporationRepository corpRepository, DriverRepository driverRepository) {
        this.clientRepository = clientRepository;
        this.corpRepository = corpRepository;
        this.driverRepository = driverRepository;
    }

    public void validateEmail(String email) {
        if (clientRepository.existsByEmail(email)
                || corpRepository.existsByEmail(email)
                || driverRepository.existsByEmail(email))
            throw new IllegalArgumentException("This e-mail was already used for another registration.");
    }
}
