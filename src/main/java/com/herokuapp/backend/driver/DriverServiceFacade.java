package com.herokuapp.backend.driver;

import org.springframework.stereotype.Service;

@Service
public class DriverServiceFacade {

    private final DriverRepository driverRepository;

    public DriverServiceFacade(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public DriverEntity getById(Long id) {
        return driverRepository.getById(id);
    }
}
