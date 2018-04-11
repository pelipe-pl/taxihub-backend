package com.herokuapp.backend.driver;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping
    public List<DriverDto> showDrivers(@PathVariable Long corpId){
        return service.findAllByCorporationId(corpId);
    }
}
