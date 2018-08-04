package com.herokuapp.backend.corporation;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "corporation")
public class CorporationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Email
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password_reset_token")
    private String passwordResetToken;

    @Column(name = "password_reset_token_active")
    private Boolean passwordResetTokenActive;

    @Column(name = "password_reset_token_validity")
    private LocalDateTime passwordResetTokenValidity;

    public CorporationEntity() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
