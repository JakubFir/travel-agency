package com.example.travelagency.integrationTests;


import com.example.travelagency.model.dto.RegisterRequest;
import com.example.travelagency.testContainers.Testcontainers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;



import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RegisterIT extends Testcontainers {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void canRegisterUser() {
        RegisterRequest registerRequest = new RegisterRequest("Test","test","test","test");
        webTestClient.post()
                .uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerRequest)
                .exchange()
                .expectStatus().isOk();
    }
}
