package com.example.demo.models;

import java.util.UUID;

public class UserSessionData {
    private String username;
    private String password;
    private UUID sessionUUID;

    public UserSessionData(String username, String password, UUID sessionUUID) {
        this.username = username;
        this.password = password;
        this.sessionUUID = sessionUUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getSessionUUID() {
        return sessionUUID;
    }

    public void setSessionUUID(UUID sessionUUID) {
        this.sessionUUID = sessionUUID;
    }
}
