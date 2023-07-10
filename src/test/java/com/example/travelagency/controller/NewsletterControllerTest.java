package com.example.travelagency.controller;

import com.example.travelagency.mapper.NewsLetterMapper;
import com.example.travelagency.model.dto.NewsLetterDto;
import com.example.travelagency.model.persistence.Newsletter;

import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.service.JwtService;
import com.example.travelagency.service.NewsLetterService;
import com.example.travelagency.service.observer.NewsLetterObservable;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@SpringJUnitWebConfig
@WebMvcTest(NewsLetterController.class)
class NewsletterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private NewsLetterService newsLetterService;
    @MockBean
    private NewsLetterMapper newsLetterMapper;
    @MockBean
    private NewsLetterObservable observable;

    @Test
    @WithMockUser
    void createNewsLetter() throws Exception {
        Newsletter newsLetter = new Newsletter(1L, "test", new ArrayList<>());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(newsLetter);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/newsletter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void getAllNewsLetters() throws Exception {
        Newsletter newsLetter = new Newsletter(1L, "test", new ArrayList<>());
        NewsLetterDto newsLetterDto = new NewsLetterDto("test", new ArrayList<>());
        List<Newsletter> list = new ArrayList<>();
        list.add(newsLetter);

        when(newsLetterService.getAllNewsLetters()).thenReturn(list);
        when(newsLetterMapper.mapToNewsletterDtoList(list)).thenReturn(List.of(newsLetterDto));

        //When && then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/newsletter"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", Matchers.is("test")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void subscribeToGivenNewsLetter() throws Exception {
        Subscriber observer = new Subscriber(1L,"test", new ArrayList<>());
        Gson gson = new Gson();
        String jsonContent = gson.toJson(observer);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/newsletter/{id}", observer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @WithMockUser
    void unsubscribeToGivenNewsLetter() throws Exception {
        Subscriber observer = new Subscriber(1L,"test", new ArrayList<>());
        Gson gson = new Gson();
        String jsonContent = gson.toJson(observer);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/newsletter/{id}", observer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}