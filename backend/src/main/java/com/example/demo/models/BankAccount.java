package com.example.demo.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bankaccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankaccount_id_seq")
    @SequenceGenerator(name = "bankaccount_id_seq", sequenceName = "bankaccount_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "balance")
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
