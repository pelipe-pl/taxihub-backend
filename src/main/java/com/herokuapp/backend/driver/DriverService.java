package com.herokuapp.backend.driver;

import com.herokuapp.backend.car.CarEntity;
import com.herokuapp.backend.car.CarServiceFacade;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final CarServiceFacade carService;

    public DriverService(DriverRepository driverRepository, CarServiceFacade carService) {
        this.driverRepository = driverRepository;
        this.carService = carService;
    }

    public DriverDto getById(Long id) {
        if (driverRepository.existsById(id)) {
            return toDto(driverRepository.getById(id));
        } else throw new NoSuchElementException("There is no driver with this id");
    }

    public void update(DriverDto driverDto) {
        if (driverDto.getId() != null && driverRepository.existsById(driverDto.getId())) {
            if (driverDto.getName() == null || driverDto.getSurname() == null)
                throw new IllegalArgumentException("The name and/or surname for driver was not provided");
            else {
                DriverEntity driverEntity = driverRepository.getById(driverDto.getId());
                driverEntity.setName(driverDto.getName());
                driverEntity.setSurname(driverDto.getSurname());
                if (driverDto.getCar() != null) {
                    CarEntity carEntity = carService.getByDriverId(driverDto.getId());
                    carEntity.setMake(driverDto.getCar().getMake());
                    carEntity.setModel(driverDto.getCar().getModel());
                    carEntity.setColor(driverDto.getCar().getColor());
                    carEntity.setPlates(driverDto.getCar().getPlates().toUpperCase());
                    carService.update(carEntity);
                }
                driverRepository.save(driverEntity);
            }
        } else throw new IllegalArgumentException("There is no driver with this id or id is was not provided");
    }

    private DriverDto toDto(DriverEntity driverEntity) {
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driverEntity.getId());
        driverDto.setName(driverEntity.getName());
        driverDto.setSurname(driverEntity.getSurname());
        driverDto.setEmail(driverEntity.getEmail());
        driverDto.setCorporationId(driverEntity.getCorporation().getId());
        if (driverEntity.getCar() != null) {
            driverDto.setCar(carService.toDto(driverEntity.getCar()));
        }
        return driverDto;
    }
}
