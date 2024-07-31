package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.models.BankAccount;
import com.example.demo.repos.AccountRepository;
import com.example.demo.repos.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    public final Long LETTER_COST = 10L;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional
    public BigDecimal registerUser(String name, String password) {
        BankAccount bankAccount = new BankAccount();
        var cost = BigDecimal.valueOf(-LETTER_COST * name.length());
        bankAccount.setBalance(cost);

        bankAccountRepository.save(bankAccount);

        Account newAccount = new Account();
        newAccount.setUsername(name);
        newAccount.setPassword(password);
        newAccount.setBankAccount(bankAccount);

        accountRepository.save(newAccount);

        bankAccountRepository.flush();
        accountRepository.flush();

        return cost;
    }
}
