package com.example.travelagency.integrationTests;

import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.model.dto.FlightRequest;
import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.model.persistence.User;
import com.example.travelagency.repository.UserRepository;
import com.example.travelagency.testContainers.Testcontainers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.Date;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingIT extends Testcontainers {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void canBookTrip(){
        String checkInDate = java.time.LocalDate.now().toString();
        String checkOutDate = java.time.LocalDate.now().plusDays(2).toString();

        RegisterRequest registerRequest = new RegisterRequest("Test2","tes2cst","te2st","te2st@wp.pl","Berlin","BER");
        webTestClient.post()
                .uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerRequest)
                .exchange()
                .expectStatus().isOk();

        Trip trip = new Trip("PAR", "Paris", "PAR");
        webTestClient.post()
                .uri("/trips").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(trip)
                .exchange()
                .expectStatus().isOk();

        BookingHotelRequest bookingHotelRequest =
                new BookingHotelRequest(checkInDate,checkOutDate,"Paris City Centre","Paris",1);

        HotelInfo hotelInfo = webTestClient
                .post()
                .uri("/hotels")
                .bodyValue(bookingHotelRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(HotelInfo.class)
                .returnResult()
                .getResponseBody();

       Long LongHotelId = hotelInfo.getResult().get(0).getHotelId();
        User user = userRepository.findUserByUsername(registerRequest.getUsername()).orElseThrow();
        FlightRequest flightRequest = new FlightRequest(java.time.LocalDate.now().plusDays(1).toString(),1L);
        BookingHotelRequest finalBookingRequest =
                new BookingHotelRequest(checkInDate,checkOutDate,"Paris City Centre","Paris",1,LongHotelId);
        BookingRequest bookingRequest = new BookingRequest(1L, flightRequest,finalBookingRequest);
        webTestClient
                .mutate()
                .responseTimeout(Duration.ofMillis(30000))
                .build()
                .post()
                .uri("/book/{userId}",user.getId()).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bookingRequest)
                .exchange()
                .expectStatus().isOk();
    }
}
