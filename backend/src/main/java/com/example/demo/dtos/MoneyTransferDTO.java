package com.example.demo.dtos;

import java.math.BigDecimal;

public class MoneyTransferDTO {
    private String receiverName;

    private BigDecimal amount;

    public MoneyTransferDTO() {

    }

    public MoneyTransferDTO(String senderName, BigDecimal amount) {
        this.receiverName = senderName;
        this.amount = amount;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
