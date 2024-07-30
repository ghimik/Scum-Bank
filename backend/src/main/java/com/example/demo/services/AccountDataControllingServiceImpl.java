package com.example.demo.services;

import com.example.demo.models.TransactionProjection;
import com.example.demo.repos.AccountRepository;
import com.example.demo.repos.BankAccountRepository;
import com.example.demo.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountDataControllingServiceImpl implements AccountDataControllingService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

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

    @Override
    public List<TransactionProjection> getTransactionsList(Long id) {
        return transactionRepository.findAllBySenderId(id).stream().map((transaction -> {
            var proj = new TransactionProjection();
            proj.setDate(transaction.getTimestamp());
            proj.setAmount(transaction.getValue());
            proj.setReceiverName(accountRepository.findById(id).orElseThrow().getUsername());
            return proj;
        })).toList();
    }
}
