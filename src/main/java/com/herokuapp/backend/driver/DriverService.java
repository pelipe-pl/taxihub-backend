package com.herokuapp.backend.driver;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<DriverDto> findAllByCorporationId(Long corporationId) {
        return driverRepository.findAllByCorporationId(corporationId)
                .stream().map(d -> new DriverDto(d))
                .collect(Collectors.toList());
    }
}
