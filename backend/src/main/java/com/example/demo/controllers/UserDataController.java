package com.example.demo.controllers;

import com.example.demo.services.UserParamsManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserDataController {

    @Autowired
    private UserParamsManagmentService userParams;

    @GetMapping("/getuserdata")
    public ResponseEntity getUserData(@RequestParam UUID uuid) {
        if (userParams.isValidUUID(uuid)) {
            return ResponseEntity.ok().body(userParams.getUserSessionData());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");

    }
}
