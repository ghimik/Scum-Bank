package com.example.demo.models.projections;

public class AccountProjection {

    private String username;

    private byte[] avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public AccountProjection(String username, byte[] avatar) {
        this.username = username;
        this.avatar = avatar;
    }

    public AccountProjection() {
    }
}
