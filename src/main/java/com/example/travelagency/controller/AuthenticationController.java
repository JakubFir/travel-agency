package com.example.travelagency.controller;

import com.example.travelagency.service.authenticationService.AuthenticationRequest;
import com.example.travelagency.service.authenticationService.AuthenticationResponse;
import com.example.travelagency.service.authenticationService.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jwt/login")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> authUser(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        System.out.println(authenticationResponse.getToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, authenticationResponse.getToken())
                .body(authenticationResponse);
    }
}
