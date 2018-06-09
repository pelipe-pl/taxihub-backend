package com.herokuapp.backend.car;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String make;

    private String model;

    private String color;

    @Column(unique = true)
    private String plates;

    public CarEntity() {
    }

    public CarEntity(String make, String model, String color, String plates) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(Id, carEntity.Id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "Id=" + Id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", plates='" + plates + '\'' +
                '}';
    }
}
