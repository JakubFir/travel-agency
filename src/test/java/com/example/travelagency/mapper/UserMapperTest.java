package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.UserDto;
import com.example.travelagency.model.persistence.Role;
import com.example.travelagency.model.persistence.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    void mapToUserDto() {
        // Given
        User user = new User("john_doe", "password123", "John Doe", "john@example.com", Role.USER);

        // When
        UserDto userDto = userMapper.mapToUserDto(user);

        // Then
        assertEquals("john_doe", userDto.getUsername());
        assertEquals("john@example.com", userDto.getEmail());
        assertEquals("John Doe", userDto.getName());
    }

    @Test
    void mapToUserDtoList() {
        // Given
        List<User> users = new ArrayList<>();
        User user1 = new User("john_doe", "password123", "John Doe", "john@example.com", Role.USER);
        users.add(user1);

        User user2 = new User("jane_smith", "password456", "Jane Smith", "jane@example.com", Role.USER);
        users.add(user2);

        // When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(users);

        // Then
        assertEquals(2, userDtoList.size());

        UserDto userDto1 = userDtoList.get(0);
        assertEquals("john_doe", userDto1.getUsername());
        assertEquals("john@example.com", userDto1.getEmail());
        assertEquals("John Doe", userDto1.getName());

        UserDto userDto2 = userDtoList.get(1);
        assertEquals("jane_smith", userDto2.getUsername());
        assertEquals("jane@example.com", userDto2.getEmail());
        assertEquals("Jane Smith", userDto2.getName());
    }
}