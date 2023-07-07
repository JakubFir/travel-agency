package com.example.travelagency.integrationTests;

import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.testContainers.Testcontainers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BookingIT extends Testcontainers {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void canBookTrip(){
        RegisterRequest registerRequest = new RegisterRequest("Test","test","test","test");
        webTestClient.post()
                .uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerRequest)
                .exchange()
                .expectStatus().isOk();

        Trip trip = new Trip("Berlin", "BER", "PAR", "Paris", "test");
        webTestClient.post()
                .uri("/trips").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(trip)
                .exchange()
                .expectStatus().isOk();

        BookingHotelRequest bookingHotelRequest =
                new BookingHotelRequest("2023-07-20","2023-07-29","Paris City Centre","Paris",1,51436L);

        BookingRequest bookingRequest = new BookingRequest(1L, 1L,bookingHotelRequest);
        webTestClient
                .mutate()
                .responseTimeout(Duration.ofMillis(30000))
                .build()
                .post()
                .uri("/book/{userId}",1).contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bookingRequest)
                .exchange()
                .expectStatus().isOk();
    }
}
