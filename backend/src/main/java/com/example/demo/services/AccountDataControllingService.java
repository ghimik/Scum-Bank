package com.example.demo.services;

import com.example.demo.models.projections.TransactionProjection;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDataControllingService {

    String getUsername(Long id);

    BigDecimal getUserBalance(Long id);

    List<TransactionProjection> getTransactionsList(Long id);
}
