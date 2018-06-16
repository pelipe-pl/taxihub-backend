package com.herokuapp.backend.car;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class CarDto {

    private Long Id;
    private String make;
    private String model;
    private String color;
    private String plates;

    public CarDto() {
    }

    public CarDto(Long id, String make, String model, String color, String plates) {
        this.Id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.plates = plates;
    }

    public CarDto(String make, String model, String color, String plates) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.plates = plates;
    }

    public Long getId() {
        return Id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }
}
