package com.example.demo.controllers.authreq;

import com.example.demo.dtos.MoneyTransferDTO;
import com.example.demo.models.UserRole;
import com.example.demo.services.MoneyTransferService;
import com.example.demo.services.UserParamsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class TransactionController implements UUIDValidationRequiredController {

    @Autowired
    private UserParamsManagementService userParams;

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

    @PostMapping("/castMoney")
    public ResponseEntity castMoney(@RequestParam String sessionUUID, @RequestParam String amount) {
        validateUUID(userParams, sessionUUID);
        if (userParams.getUserRole() != UserRole.Sigma)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not sigma sorry");

        var id = userParams.getUserid();

        moneyTransferService.castMoney(id, BigDecimal.valueOf(Double.parseDouble(amount)));
        return ResponseEntity.ok("congrats");
    }

}
