package com.herokuapp.backend.utils.idprovider;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdProviderController {

    private final IdProviderService idProviderService;

    public IdProviderController(IdProviderService idProviderService) {
        this.idProviderService = idProviderService;
    }

    @PostMapping("id")
    public Long getIdByEmailAndRole(@RequestBody IdRequest idRequest) {
        return idProviderService.getIdByEmailAndRole(idRequest.getEmail(), idRequest.getRole());
    }

}
