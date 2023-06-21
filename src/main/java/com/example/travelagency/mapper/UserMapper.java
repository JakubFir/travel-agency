package com.example.travelagency.mapper;

import com.example.travelagency.domain.User;
import com.example.travelagency.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public List<UserDto> mapToUserDtoList(List<User> allUsers) {
        return allUsers.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
