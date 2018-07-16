package com.herokuapp.backend.car;

import com.herokuapp.backend.driver.DriverServiceFacade;
import org.springframework.stereotype.Service;

@Service
public class CarServiceFacade {

    private final CarRepository carRepository;
    private final DriverServiceFacade driverService;

    public CarServiceFacade(CarRepository carRepository, DriverServiceFacade driverService) {
        this.carRepository = carRepository;
        this.driverService = driverService;
    }

    public void save(CarEntity carEntity) {
        if (carEntity != null) {
            if (!existsByPlates(carEntity.getPlates().toUpperCase())) {
                carRepository.save(carEntity);
            } else throw new IllegalArgumentException("The car with this plates number already exists.");
        }
    }

    public void update(CarDto carDto, Long driverId) {
        if (!driverService.existsById(driverId)) {
            throw new IllegalArgumentException("The driver with this id does not exist.");
        }
        if (carRepository.existsByPlatesAndDriverIdNot(carDto.getPlates().toUpperCase(), driverId)) {
            throw new IllegalArgumentException("The car with this plates number is owned by another driver.");
        } else {
            CarEntity carEntity = carRepository.getByDriver_Id(driverId);
            carEntity.setMake(carDto.getMake());
            carEntity.setModel(carDto.getModel());
            carEntity.setColor(carDto.getColor());
            carEntity.setPlates(carDto.getPlates().toUpperCase());
            carRepository.save(carEntity);
        }
    }

    public CarEntity getByDriverId(Long id) {
        return carRepository.getByDriver_Id(id);
    }

    public CarDto getDtoByDriverId(Long id) {
        if (!driverService.existsById(id))
            throw new IllegalArgumentException("There is no driver with this ID.");
        if (!carRepository.existsByDriver_Id(id))
            throw new IllegalArgumentException("This driver does not have a car registered.");
        else return toDto(carRepository.getByDriver_Id(id));
    }

    public Boolean existsByPlates(String plates) {
        return carRepository.existsByPlates(plates.toUpperCase());
    }

    public CarDto toDto(CarEntity carEntity) {
        return new CarDto(
                carEntity.getId(),
                carEntity.getMake(),
                carEntity.getModel(),
                carEntity.getColor(),
                carEntity.getPlates()
        );
    }
}
