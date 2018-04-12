package com.herokuapp.backend.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class User implements UserDetails {
    private String email;
    private Role role;
    private String name;
    private String surname;
    private Long driverId;
    private Integer corporationId;
    private Long clientId;

    public User(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public User() {
    }

    @Override
    public Collection<? extends SimpleGrantedAuthority> getAuthorities() {
        return Stream.of(Role.values()).map(r -> new SimpleGrantedAuthority(r.name())).collect(toList());
    }

    @Override
    public String getPassword() {
        return "na";
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Integer getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(Integer corporationId) {
        this.corporationId = corporationId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long userId) {
        this.clientId = userId;
    }
}
