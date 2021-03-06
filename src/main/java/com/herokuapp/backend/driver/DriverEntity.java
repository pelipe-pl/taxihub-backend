package com.herokuapp.backend.driver;

import com.herokuapp.backend.car.CarEntity;
import com.herokuapp.backend.corporation.CorporationEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @NotNull
    @Column(name = "token")
    private String token;

    @NotNull
    @Column(name = "suspended")
    private Boolean suspended;

    @Column(name = "password_reset_token")
    private String passwordResetToken;

    @Column(name = "password_reset_token_active")
    private Boolean passwordResetTokenActive;

    @Column(name = "password_reset_token_validity")
    private LocalDateTime passwordResetTokenValidity;

    private Boolean passwordSet;

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

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public Boolean getPasswordSet() {
        return passwordSet;
    }

    public void setPasswordSet(Boolean passwordSet) {
        this.passwordSet = passwordSet;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Boolean getPasswordResetTokenActive() {
        return passwordResetTokenActive;
    }

    public void setPasswordResetTokenActive(Boolean passwordResetTokenActive) {
        this.passwordResetTokenActive = passwordResetTokenActive;
    }

    public LocalDateTime getPasswordResetTokenValidity() {
        return passwordResetTokenValidity;
    }

    public void setPasswordResetTokenValidity(LocalDateTime passwordResetTokenValidity) {
        this.passwordResetTokenValidity = passwordResetTokenValidity;
    }
}