package com.example.travelagency.controller;

import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.service.UserService;
import com.example.travelagency.validator.RequestValidator;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(RegisterController.class)
class RegisterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  UserService userService;
    @MockBean
    private  RequestValidator requestValidator;

    @Test
    @WithMockUser
    void registerUser() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("test", "password", "John Doe", "johnmplem");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(registerRequest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}