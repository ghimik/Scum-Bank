package com.example.demo.models.projections;

public class AccountProjection {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountProjection(String username) {
        this.username = username;
    }

    public AccountProjection() {
    }
}
