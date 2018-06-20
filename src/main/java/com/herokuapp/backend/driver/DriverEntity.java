package com.herokuapp.backend.driver;

import com.herokuapp.backend.car.CarEntity;
import com.herokuapp.backend.corporation.CorporationEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "driver")
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "surname")
    @NotBlank
    private String surname;

    @Email
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corporation_id")
    private CorporationEntity corporation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @Column(name = "token")
    private String token;

    public Long getId() {
        return id;
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

    public CorporationEntity getCorporation() {
        return corporation;
    }

    public void setCorporation(CorporationEntity corporation) {
        this.corporation = corporation;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CarEntity getCar() {return car; }

    public void setCar(CarEntity car) {this.car = car; }
}