package com.herokuapp.backend.errors;

public class Error {

    private String description;

    public Error(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
