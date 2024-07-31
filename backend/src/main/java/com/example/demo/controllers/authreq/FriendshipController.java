package com.example.demo.controllers.authreq;

import com.example.demo.services.FriendsManagementService;
import com.example.demo.services.UserParamsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FriendshipController implements UUIDValidationRequiredController {

    @Autowired
    private UserParamsManagementService userParams;

    @Autowired
    private FriendsManagementService friendsManagementService;

    @PostMapping("/befriend")
    public ResponseEntity befriend(@RequestParam String sessionUUID,
                                   @RequestBody String friendName) {
        validateUUID(userParams, sessionUUID);
        friendsManagementService.befriend(userParams.getUserid(), friendName);
        return ResponseEntity.ok().body("");
    }
}
