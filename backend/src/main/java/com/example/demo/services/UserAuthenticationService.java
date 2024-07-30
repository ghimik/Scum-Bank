package com.example.demo.services;

import com.example.demo.models.Account;

import java.util.UUID;

public interface UserAuthenticationService {

    Account authenticate(String name, String password);

}
