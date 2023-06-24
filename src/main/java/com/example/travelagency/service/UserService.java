package com.example.travelagency.service;

import com.example.travelagency.domain.Role;
import com.example.travelagency.domain.User;
import com.example.travelagency.exceptions.UserNotFoundException;
import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.model.dto.UpdateUserRequest;
import com.example.travelagency.exceptions.EmailTakenException;
import com.example.travelagency.exceptions.UsernameTakenException;
import com.example.travelagency.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        if (checkIfUserExists(userId)) {
            userRepository.deleteById(userId);
        } else throw new UsernameNotFoundException("User not found");
    }

    public void updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        if (checkIfUserExists(userId)) {
            User userToUpdate = userRepository.findById(userId).orElseThrow();
            if (updateUserRequest.getName() != null) {
                userToUpdate.setName(updateUserRequest.getName());
            }
            if (updateUserRequest.getUsername() != null && !userToUpdate.getUsername().equals(updateUserRequest.getUsername())) {
                if (userRepository.existsByUsername(updateUserRequest.getUsername())) {
                    throw new UsernameTakenException("Username taken");
                }
                userToUpdate.setUsername(updateUserRequest.getUsername());
            }
            if (updateUserRequest.getPassword() != null) {
                userToUpdate.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
            }
            if (updateUserRequest.getEmail() != null && !userToUpdate.getEmail().equals(updateUserRequest.getEmail())) {
                if (userRepository.existsByEmail(updateUserRequest.getEmail())) {
                    throw new EmailTakenException("Email taken");
                }
                userToUpdate.setEmail(updateUserRequest.getEmail());
            }
            userRepository.save(userToUpdate);
        }

    }

    private boolean checkIfUserExists(Long userId) {
        return userRepository.existsById(userId);
    }
}
