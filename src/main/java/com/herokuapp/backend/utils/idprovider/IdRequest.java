package com.herokuapp.backend.utils.idprovider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.herokuapp.backend.auth.Role;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
class IdRequest {

    private String email;
    private Role role;

    public IdRequest() {
    }

    public IdRequest(String email, Role role) {
        this.email = email;
        this.role = role;
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
}
