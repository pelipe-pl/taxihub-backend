package com.herokuapp.backend.driver;

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
}
