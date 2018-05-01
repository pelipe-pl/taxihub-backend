package com.herokuapp.backend.driver;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceFacade {

    private final DriverRepository driverRepository;

    public DriverServiceFacade(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public DriverEntity getById(Long id) {
        return driverRepository.getById(id);
    }

    public boolean existByEmail(String email) {
        return driverRepository.existsByEmail(email);
    }

    public List<DriverDto> findByCorporation(Long corporationId) {
        return driverRepository.findAllByCorporation_Id(corporationId)
                .stream()
                .map(DriverDto::new)
                .collect(Collectors.toList());
    }
}
