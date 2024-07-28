package com.example.demo.services;

import java.math.BigDecimal;

public interface AccountDataControllingService {

    String getUsername(Long id);

    BigDecimal getUserBalance(Long userId);
}
