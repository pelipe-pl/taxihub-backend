package com.herokuapp.backend.corporation;

import com.herokuapp.backend.driver.DriverDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/corporation")
public class CorporationController {
    private final CorporationService service;

    public CorporationController(CorporationService service) {
        this.service = service;
    }

    @PostMapping("/driver")
    public void saveDriver(@RequestBody DriverDto driver) {
        service.createDriver(driver);
    }

    @GetMapping("/driver/{driverId}/sendtoken")
    public void resendDriversToken(@PathVariable Long driverId) {
        service.resendDriversToken(driverId);
    }

    @PostMapping
    public CorporationDto saveCorpo(@RequestBody CorporationDto corporation) throws ExecutionException, InterruptedException {
        return service.createCorporation(corporation);
    }

    @GetMapping("/profile/{id}")
    public CorporationDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping(value = "/name/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getName(@PathVariable Long id) {
        return Collections.singletonMap("name", service.getName(id));
    }

    @PutMapping
    public void update(@RequestBody CorporationDto corporationDto) {
        service.update(corporationDto);
    }

    @GetMapping("{id}/drivers")
    public List<DriverDto> findDrivers(@PathVariable Long id) {
        return service.findDrivers(id);
    }


    @PutMapping("/driver/{driverId}/enable")
    public void enableDriver(@PathVariable Long driverId) {
        service.changeDriverStatus(driverId, false);
    }

    @PutMapping("/driver/{driverId}/suspend")
    public void suspendDriver(@PathVariable Long driverId) {
        service.changeDriverStatus(driverId, true);
    }
}