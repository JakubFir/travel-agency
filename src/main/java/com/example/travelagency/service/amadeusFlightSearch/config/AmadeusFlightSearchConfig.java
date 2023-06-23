package com.example.travelagency.service.amadeusFlightSearch.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AmadeusFlightSearchConfig {

    @Value("${amadeus.endpoint.url}")
    private String amadeusEndpoint;
    @Value("${amadues.client.id}")
    private String clientId;
    @Value("${amadeus.client.secret}")
    private String clientSecret;

}
