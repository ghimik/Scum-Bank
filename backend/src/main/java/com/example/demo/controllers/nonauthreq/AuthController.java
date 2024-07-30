package com.example.demo.controllers.nonauthreq;

import com.example.demo.dtos.UserRequestDTO;
import com.example.demo.services.UserParamsManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        var account = userAuthenticationService.authenticate(username,password);


        ResponseEntity response;
        if (account != null) {
            var uuid = UUID.randomUUID();

            userParams.setSessionUUID(uuid);

            userParams.setUserid(account.getId());
            body.put("authorized", "true");
            body.put("sessionUUID", uuid.toString());
            response = ResponseEntity.ok().body(body);

        } else {
            body.put("authorized", "false");
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }

        return response;
    }

    @GetMapping("/unauth")
    public ResponseEntity unauthorize(@RequestParam String sessionUUID) {
        if (userParams.closeSession(sessionUUID)) {
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
    }

}


