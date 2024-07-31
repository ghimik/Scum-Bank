package com.example.demo.controllers.authreq;

import com.example.demo.dtos.MoneyTransferDTO;
import com.example.demo.services.MoneyTransferService;
import com.example.demo.services.UserParamsManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class TransactionController implements UUIDValidationRequiredController {

    @Autowired
    private UserParamsManagmentService userParams;

    @Autowired
    private MoneyTransferService moneyTransferService;

    @PostMapping("/transferMoney")
    public ResponseEntity transferMoney(@RequestParam String sessionUUID, @RequestBody MoneyTransferDTO dto) {
        validateUUID(userParams, sessionUUID);
        var body = new HashMap<String, Object>();

        Long senderId = userParams.getUserid();
        String receiverName = dto.getReceiverName();
        BigDecimal amount = dto.getAmount();
        try {
            moneyTransferService.transferMoney(senderId, receiverName, amount);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(body);
        }
        body.put("success", "true");
        return ResponseEntity.ok().body(body);

    }

}
