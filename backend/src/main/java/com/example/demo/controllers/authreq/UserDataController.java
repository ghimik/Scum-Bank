package com.example.demo.controllers.authreq;

import com.example.demo.dtos.GeneralUserInformationDTO;
import com.example.demo.services.AccountDataControllingService;
import com.example.demo.services.UserParamsManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserDataController implements UUIDValidationRequiredController {

    @Autowired
    private UserParamsManagmentService userParams;

    @Autowired
    private AccountDataControllingService accountDataControllingService;

    @GetMapping("/generalinfo")
    public ResponseEntity<GeneralUserInformationDTO> getGeneralInfo(@RequestParam String UUID) {
        validateUUID(userParams, UUID);

        var userId = userParams.getUserid();
        var username = accountDataControllingService.getUsername(userId);
        var balance = accountDataControllingService.getUserBalance(userId);

        return ResponseEntity.ok().body(new GeneralUserInformationDTO(username, balance));
    }



}
