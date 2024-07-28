package com.example.demo.dtos;


public class UserRequestDTO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRequestDTO() {

    }

    public UserRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
