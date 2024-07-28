package com.example.demo.dtos;

import java.math.BigDecimal;

public class GeneralUserInformationDTO {

    private String username;

    private BigDecimal balance;

    public GeneralUserInformationDTO() {
    }

    public GeneralUserInformationDTO(String username, BigDecimal balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
