package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.models.BankAccount;
import com.example.demo.models.Transaction;
import com.example.demo.repos.AccountRepository;
import com.example.demo.repos.BankAccountRepository;
import com.example.demo.repos.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    @Transactional
    public void transferMoney(Long senderUserId, String receiverName, BigDecimal amount) {
        Account senderAccount = accountRepository.findById(senderUserId).orElseThrow();
        BankAccount sender = senderAccount.getBankAccount();

        Account receiverAccount = accountRepository.findByUsername(receiverName);
        BankAccount reciever = receiverAccount.getBankAccount();

        Transaction transaction = new Transaction();
        transaction.setReciever(receiverAccount);
        transaction.setSender(senderAccount);
        transaction.setTimestamp(Timestamp.from(Instant.now()));

        bankAccountRepository.addMoney(reciever.getId(), amount);
        bankAccountRepository.annigilateMoney(sender.getId(), amount);

        transactionRepository.save(transaction);

    }
}
