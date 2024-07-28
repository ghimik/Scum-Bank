package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.models.UserSessionData;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Service
@SessionScope
public class UserParamsManagmentService {

    private UserSessionData userSessionData;

    public Boolean isValidUUID(UUID other) {
        return userSessionData.getSessionUUID().equals(other);
    }

    public UserSessionData getUserSessionData() {
        return userSessionData;
    }

    public void setUserSessionData(UserSessionData userSessionData) {
        this.userSessionData = userSessionData;
    }
}
