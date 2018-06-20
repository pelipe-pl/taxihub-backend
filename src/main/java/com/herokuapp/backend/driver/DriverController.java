package com.herokuapp.backend.driver;

import com.herokuapp.backend.car.CarDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
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
        driverService.carUpdate(carDto, driverId);
    }
}
