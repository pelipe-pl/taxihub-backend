package com.herokuapp.backend.corporation;

import com.herokuapp.backend.driver.DriverDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/corporation")
public class CorporationController {
    private final CorporationService service;

    public CorporationController(CorporationService service) {
        this.service = service;
    }

    @PostMapping("/{id}/driver")
    public DriverDto saveDriver(@RequestBody DriverDto driver, @PathVariable Long id) {
        return service.createDriver(driver, id);
    }

    @PostMapping
    public CorporationDto saveCorpo(@RequestBody CorporationDto corporation) throws ExecutionException, InterruptedException {
        return service.createCorporation(corporation);
    }
}