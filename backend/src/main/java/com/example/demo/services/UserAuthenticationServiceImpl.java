package com.example.demo.services;

import com.example.demo.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Boolean authenticate(String name, String password) {
        return accountRepository.findByUsernameAndPassword(name, password) != null;
    }
}
