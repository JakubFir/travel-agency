package com.example.travelagency.validator;

import com.example.travelagency.exceptions.EmptyFieldsException;
import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.exceptions.EmailTakenException;
import com.example.travelagency.exceptions.UsernameTakenException;
import com.example.travelagency.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean validateRegisterRequest(RegisterRequest registerRequest) {
        String password = registerRequest.getPassword();
        if (password == null || password.length() < 5 ) {
            throw new EmptyFieldsException("password has to be at least 5 characters long");
        }
        return validateEmail(registerRequest.getEmail()) && validateUsername(registerRequest.getUsername());
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
