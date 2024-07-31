package com.example.demo.models.projections;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionProjection {

    private String receiverName;

    private Timestamp date;

    private BigDecimal amount;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
