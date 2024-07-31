package com.example.demo.services;

import java.math.BigDecimal;

public interface MoneyTransferService {

    void transferMoney (Long senderId, String receiverName, BigDecimal amount) throws Exception;
}
