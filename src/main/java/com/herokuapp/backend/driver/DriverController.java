package com.herokuapp.backend.driver;

import com.herokuapp.backend.car.CarDto;
import com.herokuapp.backend.car.CarServiceFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;
    private final CarServiceFacade carService;

    public DriverController(DriverService driverService, CarServiceFacade carService) {
        this.driverService = driverService;
        this.carService = carService;
    }

    @GetMapping("/profile/{id}")
    public DriverDto getById(@PathVariable Long id) {
        return driverService.getById(id);
    }

    @PutMapping
    public void update(@RequestBody DriverDto driverDto) {
        driverService.update(driverDto);
    }

    @PostMapping("{driverId}/car")
    public void carUpdate(@RequestBody CarDto carDto, @PathVariable Long driverId){
        carService.update(carDto, driverId);
    }
}
