package com.example.demo.controllers.nonauthreq;

import com.example.demo.dtos.UserRequestDTO;
import com.example.demo.services.UserRegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserRegistrationServiceImpl userRegistrationService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequestDTO requestDTO) {
        HashMap<String, Object> body = new HashMap<>();
        BigDecimal cost;
        try {
            cost = userRegistrationService.registerUser(requestDTO.getUsername(), requestDTO.getPassword());
        }
        catch (Exception e) {
            body.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(body);
        }
        body.put("user", requestDTO);
        body.put("cost", cost);
        return ResponseEntity.ok().body(body);
    }
}
