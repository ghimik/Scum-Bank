package com.example.demo.controllers;

import com.example.demo.dtos.GeneralUserInformationDTO;
import com.example.demo.services.AccountDataControllingService;
import com.example.demo.services.UserParamsManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserDataController {

    @Autowired
    private UserParamsManagmentService userParams;

    @Autowired
    private AccountDataControllingService accountDataControllingService;

    @GetMapping("/generalinfo")
    public ResponseEntity<GeneralUserInformationDTO> getGeneralInfo(@RequestParam String UUID) {

        if (!userParams.isValidUUID(UUID)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        var userId = userParams.getUserid();
        var username = accountDataControllingService.getUsername(userId);
        var balance = accountDataControllingService.getUserBalance(userId);

        return ResponseEntity.ok().body(new GeneralUserInformationDTO(username, balance));
    }




}
