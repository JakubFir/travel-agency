package com.example.travelagency.validator;

import com.example.travelagency.dto.RegisterRequest;
import com.example.travelagency.exceptions.EmailTakenException;
import com.example.travelagency.exceptions.UsernameTakenException;
import com.example.travelagency.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestValidator {

    private final UserRepository userRepository;

    public boolean validateRegisterRequest(RegisterRequest registerRequest) {
        if (validateEmail(registerRequest.getEmail()) && validateUsername(registerRequest.getUsername())) {
            return true;
        }
        return false;
    }

    private boolean validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameTakenException("Username already taken");
        }
        return true;
    }

    private boolean validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailTakenException("Email already taken");
        }
        return true;
    }
}
