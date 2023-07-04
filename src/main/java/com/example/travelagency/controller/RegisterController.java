package com.example.travelagency.controller;

import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.exceptions.EmptyFieldsException;
import com.example.travelagency.service.UserService;
import com.example.travelagency.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final RequestValidator requestValidator;

    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest ) {
        requestValidator.validateRegisterRequest(registerRequest);
        try {
            userService.registerUser(registerRequest);
        } catch (Exception e) {
            throw new EmptyFieldsException("All fields must be filled");
        }
        return ResponseEntity.ok().build();
    }
}
