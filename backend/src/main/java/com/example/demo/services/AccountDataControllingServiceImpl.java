package com.example.demo.services;

import com.example.demo.repos.AccountRepository;
import com.example.demo.repos.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountDataControllingServiceImpl implements AccountDataControllingService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public String getUsername(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow()
                .getUsername();
    }

    @Override
    public BigDecimal getUserBalance(Long userId) {
        return bankAccountRepository
                .findByMainAccountId(userId)
                .getBalance();
    }
}
