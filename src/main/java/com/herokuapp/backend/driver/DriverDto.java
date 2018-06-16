package com.herokuapp.backend.driver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.herokuapp.backend.car.CarDto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class DriverDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Long corporationId;
    private CarDto car;

    public DriverDto() {
    }

    public DriverDto(DriverEntity d) {
        this.id = d.getId();
        this.name = d.getName();
        this.surname = d.getSurname();
        this.email = d.getEmail();
        this.corporationId = d.getCorporation().getId();
    }

    public DriverDto(Long id, String name, String surname, String email, Long corporationId, CarDto car) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.corporationId = corporationId;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCorporationId(Long corporationId) {
        this.corporationId = corporationId;
    }

    public Long getCorporationId() {
        return corporationId;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }
}