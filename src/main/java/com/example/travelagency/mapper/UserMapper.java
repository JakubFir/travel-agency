package com.example.travelagency.mapper;

import com.example.travelagency.model.persistence.User;
import com.example.travelagency.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .origin(user.getOrigin())
                .originIataCode(user.getOriginIataCode())
                .build();
    }

    public List<UserDto> mapToUserDtoList(List<User> allUsers) {
        return allUsers.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
