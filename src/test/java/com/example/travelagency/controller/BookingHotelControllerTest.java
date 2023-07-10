package com.example.travelagency.controller;

import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.service.BookingHotelService;
import com.example.travelagency.service.JwtService;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
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

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(BookingHotelController.class)
class BookingHotelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private BookingHotelSearch bookingHotelSearch;
    @MockBean
    private BookingHotelService bookingHotelService;

    @Test
    @WithMockUser
    void getHotels() throws Exception {
        //Given
        String destination = "London";

        //When && Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/hotels/{destination}", destination))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()));
    }

    @Test
    @WithMockUser
    void getHotelsByCoordinates() throws Exception {
        //Given
        BookingHotelRequest bookingHotelRequest = new BookingHotelRequest();
        bookingHotelRequest.setCheckInDate("2023-07-01");
        bookingHotelRequest.setCheckOutDate("2023-07-05");
        bookingHotelRequest.setPlaceName("London");
        bookingHotelRequest.setDestination("New York");
        bookingHotelRequest.setAdultsNumber(2);
        bookingHotelRequest.setHotelId(123456L);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookingHotelRequest);

        when(bookingHotelService.getHotelsByCoordinates(bookingHotelRequest)).thenReturn(new HotelInfo(new ArrayList<>()));

        //When && Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .with(csrf())
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()));
    }
}