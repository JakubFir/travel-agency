package com.example.travelagency.controller;

import com.example.travelagency.exceptions.BadEmailRequest;
import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.exceptions.EmptyFieldsException;
import com.example.travelagency.service.UserService;
import com.example.travelagency.validator.RequestValidator;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final RequestValidator requestValidator;

    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (requestValidator.validateRegisterRequest(registerRequest)) {
            try {
                userService.registerUser(registerRequest);
            } catch (ConstraintViolationException e) {
                throw new BadEmailRequest("Provide a valid information's");
            }
        }
        return ResponseEntity.ok().build();
    }
}
