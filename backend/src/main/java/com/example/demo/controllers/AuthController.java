package com.example.demo.controllers;

import com.example.demo.dtos.UserRequestDTO;
import com.example.demo.models.Account;
import com.example.demo.models.UserSessionData;
import com.example.demo.services.UserParamsManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.UserAuthenticationService;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api")

public class AuthController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private UserParamsManagmentService userParams;

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody UserRequestDTO authenticationDTO) {
        HashMap<String, Object> body = new HashMap<>();
        var username = authenticationDTO.getUsername();
        var password = authenticationDTO.getPassword();
        var uuid = UUID.randomUUID();
        var userSessionData = new UserSessionData(username, password, uuid);

        userParams.setUserSessionData(userSessionData);
        if (userAuthenticationService.authenticate(username,password)) {
            body.put("authorized", "true");
            body.put("sessiondata", userSessionData);

        } else {
            body.put("authorized", "false");
        }

        var ret = ResponseEntity.ok().body(body);
        return ret;
    }

}


