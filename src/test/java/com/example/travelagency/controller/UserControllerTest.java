package com.example.travelagency.controller;

import com.example.travelagency.mapper.UserMapper;
import com.example.travelagency.model.dto.UpdateUserRequest;
import com.example.travelagency.model.dto.UserDto;
import com.example.travelagency.model.persistence.Role;
import com.example.travelagency.model.persistence.User;
import com.example.travelagency.repository.UserRepository;
import com.example.travelagency.service.UserService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;

    @Test
    @WithMockUser
    void getAllUsers() throws Exception {
        //Given
        User user = new User("test", "test", "test", "test","Paris","PAR",Role.USER);
        List<User> listOfUsers = new ArrayList<>();
        UserDto userDto = new UserDto("test", "test", "test","Pris","PAR");
        listOfUsers.add(user);
        when(userService.getAllUsers()).thenReturn(listOfUsers);
        when(userMapper.mapToUserDtoList(listOfUsers)).thenReturn(List.of(userDto));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("test")));
    }

    @Test
    @WithMockUser
    void getUser() throws Exception {
        //Given
        User user = new User("test", "test", "test", "test","Paris","PAR",Role.USER);
        UserDto userDto = new UserDto("test", "test", "test","Pris","PAR");


        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(userService.getUser(1L)).thenReturn(user);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("test")));

    }

    @Test
    @WithMockUser
    void deleteUser() throws Exception {
        //Given
        Long id = 1L;

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/users/1")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void updateUser() throws Exception {
        User user = new User("test", "test", "test", "test","Paris","PAR",Role.USER);
        UpdateUserRequest request = new UpdateUserRequest("update","update","update","update");
        UserDto userDto = new UserDto("update", "update", "test","Pris","PAR");
        when(userService.updateUser(1L,request)).thenReturn(user);
        when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);
        Long id = 1L;
        Gson gson = new Gson();
        String jsonContent = gson.toJson(request);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("update")));
    }
}