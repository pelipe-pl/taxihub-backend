package com.herokuapp.backend.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class User implements UserDetails {
    private String email;
    private Role role;

    public User(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    @Override
    public Collection<? extends SimpleGrantedAuthority> getAuthorities() {
        return Stream.of(Role.values()).map(r -> new SimpleGrantedAuthority(r.name())).collect(toList());
    }

    @Override
    public String getPassword() {
        return null;
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
}
