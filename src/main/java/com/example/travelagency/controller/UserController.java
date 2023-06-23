package com.example.travelagency.controller;

import com.example.travelagency.model.dto.UpdateUserRequest;
import com.example.travelagency.model.dto.UserDto;
import com.example.travelagency.mapper.UserMapper;
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
    public List<UserDto> getAllUsers(){
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @GetMapping(path = "/{userId}")
    public UserDto getUser(@PathVariable Long userId){
        return userMapper.mapToUserDto(userService.getUser(userId));
    }

    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest){
        userService.updateUser(userId, updateUserRequest);
        return ResponseEntity.ok().build();
    }

}
