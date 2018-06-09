package com.herokuapp.backend.car;

import org.springframework.stereotype.Service;

@Service
public class CarServiceFacade {

    private CarRepository carRepository;

    public CarServiceFacade(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void save(CarEntity carEntity) {
        if (carEntity != null) carRepository.save(carEntity);
    }
}
