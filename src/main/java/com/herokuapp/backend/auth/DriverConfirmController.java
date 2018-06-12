package com.herokuapp.backend.auth;

import com.herokuapp.backend.driver.DriverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class DriverConfirmController {

    private final DriverService driverService;

    public DriverConfirmController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping(path = "/confirm")
    public Boolean confirm(@RequestBody DriverConfirm driverConfirm) throws ExecutionException, InterruptedException {
        return driverService.confirmAndSetPass(driverConfirm);
    }
}
