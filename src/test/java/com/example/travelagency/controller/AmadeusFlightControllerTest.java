package com.example.travelagency.controller;

import com.example.travelagency.mapper.TripMapper;
import com.example.travelagency.model.dto.amadeusModel.AmadeusFlight;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringJUnitWebConfig
@WebMvcTest(AmadeusFlightController.class)
class AmadeusFlightControllerTest {

    @MockBean
    private AmadeusFlightSearch amadeusFlightSearch;
    @MockBean
    private TripMapper tripMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void getAvailableFlights() throws Exception {
        AmadeusFlight amadeusFlight = new AmadeusFlight(new ArrayList<>());
        Trip trip = new Trip("test","test","test");
        when(amadeusFlightSearch.getAvailableFlights(any(),any())).thenReturn(amadeusFlight);
        Long id = 1L;
        Gson gson = new Gson();
        String jsonContent = gson.toJson(trip);

        //When && Then
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/flight/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonContent)
                                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}