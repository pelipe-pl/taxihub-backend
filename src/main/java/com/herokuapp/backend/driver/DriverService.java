package com.herokuapp.backend.driver;

import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public DriverDto getById(Long id) {
        if (driverRepository.existsById(id)) {
            return new DriverDto(driverRepository.getById(id));
        } else throw new IllegalArgumentException("There is no driver with this id");
    }

    public void update(DriverDto driverDto) {
        if (driverDto.getId() != null && driverRepository.existsById(driverDto.getId())) {
            if (driverDto.getName() == null || driverDto.getSurname() == null)
                throw new IllegalArgumentException("The name and/or surname for driver was not provided");
            else {
                DriverEntity driverEntity = driverRepository.getById(driverDto.getId());
                driverEntity.setName(driverDto.getName());
                driverEntity.setSurname(driverDto.getSurname());
                driverRepository.save(driverEntity);
            }
        } else throw new IllegalArgumentException("There is no driver with this id or id is was not provided");
    }
}
