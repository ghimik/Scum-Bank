package com.example.demo.controllers.authreq;

import com.example.demo.dtos.GeneralUserInformationDTO;
import com.example.demo.models.projections.AccountProjection;
import com.example.demo.models.projections.TransactionProjection;
import com.example.demo.services.AccountDataControllingService;
import com.example.demo.services.FriendsManagementService;
import com.example.demo.services.UserParamsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserDataController implements UUIDValidationRequiredController {

    @Autowired
    private UserParamsManagementService userParams;

    @Autowired
    private AccountDataControllingService accountDataControllingService;

    @Autowired
    private FriendsManagementService friendsManagementService;

    @GetMapping("/generalinfo")
    public ResponseEntity<GeneralUserInformationDTO> getGeneralInfo(@RequestParam String sessionUUID) {
        validateUUID(userParams, sessionUUID);

        var userId = userParams.getUserid();
        var username = accountDataControllingService.getUsername(userId);
        var balance = accountDataControllingService.getUserBalance(userId);

        return ResponseEntity.ok().body(new GeneralUserInformationDTO(username, balance));
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam String sessionUUID) {
        validateUUID(userParams, sessionUUID);

        var userId = userParams.getUserid();
        var balance = accountDataControllingService.getUserBalance(userId);

        return ResponseEntity.ok().body(balance);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionProjection>> getTransactionsList
            (@RequestParam String sessionUUID,
             @RequestParam(required = false) Optional<Date> from) {
        validateUUID(userParams, sessionUUID);

        var userId = userParams.getUserid();
        var transactions = accountDataControllingService.getTransactionsList(userId);
        /*
        * HAHAHAHAHAHHAHAHAHA
        * HAHAHAHAHAHAHAHAHAHHA
        * HAHAHAHHAHAHHAHAHAHAHAHA
        * HAHAHAHAHAHAHAHAHHAHAHAH
        */
        return from
                .map(date ->
                        ResponseEntity
                                .ok()
                                .body(transactions
                                        .stream()
                                        .filter
                                                (transactionProjection ->
                                                        transactionProjection
                                                                .getDate()
                                                                .after(date))
                                        .toList()))
                .orElseGet(() -> ResponseEntity.ok().body(transactions));
    }

    @GetMapping("/friends")
    public ResponseEntity<List<AccountProjection>> getFriends(@RequestParam String sessionUUID) {
        validateUUID(userParams,sessionUUID);
        var id = userParams.getUserid();
        return ResponseEntity.ok(friendsManagementService.getAllFriends(id));
    }


}
