package com.example.travelagency.controller;

import com.example.travelagency.model.dto.UpdateUserRequest;
import com.example.travelagency.model.dto.UserDto;
import com.example.travelagency.mapper.UserMapper;
import com.example.travelagency.model.persistence.User;
import com.example.travelagency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userMapper.mapToUserDtoList(userService.getAllUsers()));
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(userMapper.mapToUserDto(userService.getUser(userId)));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();

    }

    @PutMapping(path = "/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest){
        return userMapper.mapToUserDto(userService.updateUser(userId, updateUserRequest));
    }

}
