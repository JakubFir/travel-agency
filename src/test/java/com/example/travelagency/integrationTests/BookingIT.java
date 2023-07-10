package com.example.travelagency.integrationTests;

import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.model.persistence.Trip;
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

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingIT extends Testcontainers {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    public void canBookTrip(){
        RegisterRequest registerRequest = new RegisterRequest("Test2","tes2cst","te2st","te2st@wp.pl","Paris","PAR");
        webTestClient.post()
                .uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerRequest)
                .exchange()
                .expectStatus().isOk();

        Trip trip = new Trip("BER", "Berlin", "PAR");
        webTestClient.post()
                .uri("/trips").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(trip)
                .exchange()
                .expectStatus().isOk();

        BookingHotelRequest bookingHotelRequest =
                new BookingHotelRequest("2023-07-20","2023-07-29","Paris City Centre","Paris",1);

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

        BookingHotelRequest finalBookingRequest =
                new BookingHotelRequest("2023-07-20","2023-07-29","Paris City Centre","Paris",1,LongHotelId);
        BookingRequest bookingRequest = new BookingRequest(1L, 1L,finalBookingRequest);
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
