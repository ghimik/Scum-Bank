package com.example.demo.services;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Service
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserParamsManagmentService {

    private UUID sessionUUID;

    private Long userid;

    public UUID getSessionUUID() {
        return sessionUUID;
    }

    public void setSessionUUID(UUID sessionUUID) {
        this.sessionUUID = sessionUUID;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Boolean isValidUUID(UUID other) {
        return this.sessionUUID != null && sessionUUID.equals(other);
    }

    public Boolean isValidUUID(String other) {
        return this.sessionUUID != null &&sessionUUID.toString().equals(other);
    }


}
