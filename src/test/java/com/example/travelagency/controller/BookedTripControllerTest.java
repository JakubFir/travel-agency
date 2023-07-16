package com.example.travelagency.controller;

import com.example.travelagency.mapper.BookedTripMapper;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.service.BookingTripService;
import com.example.travelagency.service.JwtService;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(BookedTripController.class)
class BookedTripControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private  BookingTripService bookingTripService;
    @MockBean
    private BookedTripMapper bookedTripMapper;

    @Test
    @WithMockUser
    void bookTrip() throws Exception {
        Long userId = 123L;
        BookingRequest bookingRequest = new BookingRequest();

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookingRequest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/book/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getBookedTrips() throws Exception {
        Long userId = 123L;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/book/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()));
    }
}