package com.example.demo.controllers.authreq;

import com.example.demo.services.AccountDataControllingService;
import com.example.demo.services.UserParamsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AvatarController implements UUIDValidationRequiredController {

    @Autowired
    private UserParamsManagementService userParams;

    @Autowired
    private AccountDataControllingService accountDataControllingService;

    @GetMapping("/avatar")
    public ResponseEntity<byte[]> getAvatar(@RequestParam String sessionUUID) {
        validateUUID(userParams, sessionUUID);
        var id = userParams.getUserid();
        return  ResponseEntity.ok(accountDataControllingService.getAvatar(id));
    }

    @PostMapping("/avatar")
    public ResponseEntity setAvatar(@RequestParam String sessionUUID, @RequestBody MultipartFile blob) throws IOException {
        validateUUID(userParams, sessionUUID);
        var id = userParams.getUserid();
        accountDataControllingService.setAvatar(blob, id);

        return  ResponseEntity.ok("");
    }
}
