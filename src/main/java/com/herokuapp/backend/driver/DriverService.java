package com.herokuapp.backend.driver;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public DriverDto getById(Long id) {
        if (driverRepository.existsById(id)) {
            return new DriverDto(driverRepository.getById(id));
        } else throw new NoSuchElementException("There is no driver with this id");
    }
}
