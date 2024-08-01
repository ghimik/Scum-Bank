package com.example.demo.services;

import com.example.demo.models.UserRole;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Service
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserParamsManagementService {

    private UUID sessionUUID;

    private Long userid;

    private UserRole userRole;

    public Boolean isValidUUID(UUID other) {
        return this.sessionUUID != null && sessionUUID.equals(other);
    }

    public Boolean closeSession(String sessionUUID) {
        if (this.isValidUUID(sessionUUID)) {
            this.sessionUUID = null;
            this.userid = null;
            return true;
        }
        return false;
    }

    public Boolean isValidUUID(String other) {
        return this.sessionUUID != null &&sessionUUID.toString().equals(other);
    }



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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
