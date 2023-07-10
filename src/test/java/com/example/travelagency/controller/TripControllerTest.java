package com.example.travelagency.controller;

import com.example.travelagency.mapper.TripMapper;
import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Role;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.model.persistence.TripInfo;
import com.example.travelagency.model.persistence.User;
import com.example.travelagency.service.TripService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringJUnitWebConfig
@WebMvcTest(TripController.class)
class TripControllerTest {
    @MockBean
    private TripService tripService;
    @MockBean
    private TripMapper tripMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void addTrip() throws Exception {
        Trip trip = new Trip("test", "test", "test");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(trip);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser
    void getListOfTrips() throws Exception {
        Trip trip = new Trip("test", "test", "test");
        List<Trip> list = new ArrayList<>();
        list.add(trip);
        List<TripDto> list1 = new ArrayList<>();
        TripDto tripDto = new TripDto("test", "test", "test");
        list1.add(tripDto);
        when(tripService.getListOfTrips()).thenReturn(list);
        when(tripMapper.mapToTripDtoList(list)).thenReturn(list1);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/trips"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].destination", Matchers.is("test")));
    }

    @Test
    @WithMockUser
    void getTripInfo() throws Exception {
        Trip trip = new Trip("test", "test", "test");

        TripInfo tripInfo = new TripInfo(trip, new ArrayList<>(), new ArrayList<>());

        Long id = 1L;
        when(tripService.getTripInfo(1L,1L)).thenReturn(tripInfo);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/trips/{tripId}/{userId}", id,id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trip.destination", Matchers.is("test")));
    }
}