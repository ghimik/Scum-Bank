package com.example.demo.services;

import java.math.BigDecimal;

public interface UserRegistrationService {

    BigDecimal registerUser(String name, String password);
}
