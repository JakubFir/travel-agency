package com.example.travelagency.service;

import com.example.travelagency.model.persistence.Role;
import com.example.travelagency.model.persistence.User;
import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.model.dto.UpdateUserRequest;
import com.example.travelagency.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository,passwordEncoder);
    }

    @Test
    void registerUser() {
        //Given
        RegisterRequest registerRequest = new RegisterRequest("Test","test","test","test","Paris","PAR");
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("test");

        //When
        userService.registerUser(registerRequest);

        //Then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
    }

    @Test
    void getUser() {
        //Given
        User user = new User("Test", "test","test","test","Paris","PAR", Role.USER);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        //When
        User result = userService.getUser(1L);

        //Then
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getName()).isEqualTo(user.getName());
    }

    @Test
    void getAllUsers() {
        //Given
        List<User> listOfUsers = new ArrayList<>();
        User user = new User("Test", "test","test","test", "Paris","PAR",Role.USER);
        listOfUsers.add(user);
        when(userRepository.findAll()).thenReturn(listOfUsers);

        //When
        List<User> result = userService.getAllUsers();

        //Then

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo(user.getName());
    }

    @Test
    void deleteUser() {
        //Given
        Long id = 1L;
        when(userRepository.existsById(id)).thenReturn(true);

        //When
        userService.deleteUser(id);

        //Then
        verify(userRepository).deleteById(id);
    }

    @Test
    void updateUser() {
        //Given
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Jakub");
        User user = new User("Test", "test","test","test","Paris","PAR", Role.USER);
        when(userRepository.existsById(any())).thenReturn(true);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        //When
        userService.updateUser(1L, updateUserRequest);

        //Then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        assertThat(userArgumentCaptor.getValue().getName()).isEqualTo(updateUserRequest.getName());
        assertThat(userArgumentCaptor.getValue().getEmail()).isEqualTo(user.getEmail());



    }
}