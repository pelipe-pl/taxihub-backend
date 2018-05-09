package com.herokuapp.backend.driver;

import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public DriverDto getById(Long id) {
        return new DriverDto(driverRepository.getById(id));
    }
}
