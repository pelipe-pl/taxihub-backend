package com.herokuapp.backend.corporation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CorporationNotFoundException extends RuntimeException {
    public CorporationNotFoundException(Integer id){
        super("Requested corporation " + id + " not found.");
    }
}
