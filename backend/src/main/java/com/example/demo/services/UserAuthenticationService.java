package com.example.demo.services;

import com.example.demo.models.Account;

public interface UserAuthenticationService {

    public Account authenticate(String name, String password);
}
