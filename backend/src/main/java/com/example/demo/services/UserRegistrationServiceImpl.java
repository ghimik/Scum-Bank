package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private AccountRepository accountRepository;

    public void registerUser(String name, String password) {
        Account newAccount = new Account();
        newAccount.setUsername(name);
        newAccount.setPassword(password);
        accountRepository.saveAndFlush(newAccount);
    }
}
