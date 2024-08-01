package com.example.demo.services;

import com.example.demo.models.Transaction;
import com.example.demo.models.projections.TransactionProjection;
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
        var myName = accountRepository.findById(id).orElseThrow().getUsername();
        return  transactionRepository
                .findAllByReceiverIdOrSenderId(id, id)
                .stream()
                .map(TransactionProjection::from)
                .peek(transactionProjection -> transactionProjection.replaceMyName(myName))
                .toList();


    }
}
