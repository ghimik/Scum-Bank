package com.example.demo.models.projections;

import com.example.demo.models.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionProjection {

    private String senderName;

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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void replaceMyName(String myName) {
        if (this.getSenderName().equals(myName))
            this.setSenderName("me");

        if (this.getReceiverName().equals(myName))
            this.setReceiverName("me");
    }

    public static TransactionProjection from(Transaction transaction) {
        var proj = new TransactionProjection();
        proj.setDate(transaction.getTimestamp());
        proj.setAmount(transaction.getValue());
        proj.setReceiverName(transaction.getReciever().getUsername());
        proj.setSenderName(transaction.getSender().getUsername());
        return proj;
    }

}
