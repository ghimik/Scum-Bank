package com.example.demo.controllers.authreq;

import com.example.demo.dtos.MoneyTransferDTO;
import com.example.demo.services.MoneyTransferService;
import com.example.demo.services.UserParamsManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController("/api")
public class TransactionController implements UUIDValidationRequiredController {

    @Autowired
    private UserParamsManagmentService userParamsManagmentService;

    @Autowired
    private MoneyTransferService moneyTransferService;

    @PostMapping("/transferMoney")
    public ResponseEntity transferMoney(@RequestParam String sessionUUID, @RequestBody MoneyTransferDTO dto) {
        validateUUID(userParamsManagmentService, sessionUUID);

        Long senderId = userParamsManagmentService.getUserid();
        String receiverName = dto.getReceiverName();
        BigDecimal amount = dto.getAmount();

        moneyTransferService.transferMoney(senderId, receiverName, amount);

        return ResponseEntity.ok().body("");

    }

}
