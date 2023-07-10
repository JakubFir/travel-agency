package com.example.travelagency.controller;

import com.example.travelagency.mapper.SubscriberMapper;
import com.example.travelagency.model.dto.SubscriberDto;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.service.JwtService;
import com.example.travelagency.service.SubscriberService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(SubscriberController.class)
class ObserverControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private  SubscriberService subscriberService;
    @MockBean
    private  SubscriberMapper subscriberMapper;

    @Test
    @WithMockUser
    void getAllSubscribers() throws Exception {
        //Given
        List<SubscriberDto> list = new ArrayList<>();
        SubscriberDto subscriberDto = new SubscriberDto("test");
        list.add(subscriberDto);
        Subscriber observer = new Subscriber(1L,"test",new ArrayList<>());

        when(subscriberMapper.mapToSubscriberDtoList(anyList())).thenReturn(list);
        when(subscriberService.getAllSubscribers()).thenReturn(List.of(observer));

        //When && Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/subscribers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.[0].email", Matchers.is("test")));
    }


}