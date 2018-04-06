package com.herokuapp.backend.driver;
//import com.herokuapp.backend.car.CarDto;
//import com.herokuapp.backend.car.CarEntity;

import javax.persistence.*;

@Entity
@Table(name = "driver", catalog = "postgres")
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "corporationId")
    private Long corporationId;

    @OneToOne
//    private CarEntity car;

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

    public Long getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(Long corporationId) {
        this.corporationId = corporationId;
    }

//    public CarEntity getCar() {
//        return car;
//    }
//
//    public void setCar(CarEntity car) {
//        this.car = car;
//    }
}