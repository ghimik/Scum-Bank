package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.models.BankAccount;
import com.example.demo.models.Transaction;
import com.example.demo.repos.AccountRepository;
import com.example.demo.repos.BankAccountRepository;
import com.example.demo.repos.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService{

    public final BigDecimal FRIENDS_MONEY_TRANSFER_COMISSION = BigDecimal.valueOf(0.1D);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private FriendsManagementService friendsManagementService;

    @Override
    @Transactional
    public void transferMoney(Long senderUserId, String receiverName, BigDecimal amount) throws Exception {
        if (accountRepository
                .findById(senderUserId)
                .orElseThrow()
                .getBankAccount()
                .getBalance()
                .compareTo(amount) < 0) {
            throw new Exception("not enough money");
        }

        var scaled = amount.setScale(accountRepository.NUMERIC_SCALE_FACTOR, RoundingMode.HALF_UP);

        Account senderAccount = accountRepository.findById(senderUserId).orElseThrow();
        BankAccount sender = senderAccount.getBankAccount();

        Account receiverAccount = accountRepository.findByUsername(receiverName);
        BankAccount reciever = receiverAccount.getBankAccount();


        Transaction transaction = new Transaction();
        transaction.setValue(scaled);
        transaction.setReciever(receiverAccount);
        transaction.setSender(senderAccount);
        transaction.setTimestamp(Timestamp.from(Instant.now()));


        bankAccountRepository.annigilateMoney(sender.getId(), scaled);
        if (friendsManagementService.areFriends(senderAccount, receiverAccount))
            scaled = scaled.multiply(BigDecimal.ONE.subtract(FRIENDS_MONEY_TRANSFER_COMISSION));

        bankAccountRepository.addMoney(reciever.getId(), scaled);

        transactionRepository.saveAndFlush(transaction);

    }

    @Override
    @Transactional
    public void castMoney(Long id, BigDecimal amount) {
        var bankAccount = accountRepository.findById(id).orElseThrow().getBankAccount();
        var newBalance = bankAccount.getBalance().add(amount);
        bankAccount.setBalance(newBalance);
        bankAccountRepository.flush();
    }
}
