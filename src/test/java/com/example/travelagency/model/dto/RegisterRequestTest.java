package com.example.travelagency.model.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    @Test
    void testRegisterRequestBuilder() {
        // Given
        String username = "john_doe";
        String password = "password123";
        String name = "John Doe";
        String email = "john@example.com";

        // When
        RegisterRequest registerRequest = new RegisterRequest.RegisterRequestBuilder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .build();

        // Then
        assertEquals(username, registerRequest.getUsername());
        assertEquals(password, registerRequest.getPassword());
        assertEquals(name, registerRequest.getName());
        assertEquals(email, registerRequest.getEmail());
    }
}