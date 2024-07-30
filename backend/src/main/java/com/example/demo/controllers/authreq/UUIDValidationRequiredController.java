package com.example.demo.controllers.authreq;

import com.example.demo.services.UserParamsManagmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UUIDValidationRequiredController {
    default void validateUUID(UserParamsManagmentService userParams, String UUID) {
        if (!userParams.isValidUUID(UUID)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid UUID");
        }
    }

}
