package com.herokuapp.backend.car;

import org.springframework.stereotype.Service;

@Service
public class CarServiceFacade {

    private CarRepository carRepository;

    public CarServiceFacade(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void save(CarEntity carEntity) {
        if (carEntity != null) {
            if (!existsByPlates(carEntity.getPlates().toUpperCase())) {
                carRepository.save(carEntity);
            } else throw new IllegalArgumentException("The car with this plates number already exists.");
        }
    }

    public CarEntity getByDriverId(Long id){ return carRepository.getByDriver_Id(id);}

    public void update(CarEntity carEntity) {
        carRepository.save(carEntity);
    }

    public Boolean existsByPlates(String plates) {return carRepository.existsByPlates(plates.toUpperCase());}

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
