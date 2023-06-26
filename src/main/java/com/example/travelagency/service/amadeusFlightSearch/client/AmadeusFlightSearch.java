package com.example.travelagency.service.amadeusFlightSearch.client;


import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.service.amadeusFlightSearch.config.AmadeusFlightSearchConfig;
import com.example.travelagency.model.dto.amadeusModel.AccessTokenResponse;
import com.example.travelagency.model.dto.amadeusModel.AmadeusFlight;
import com.example.travelagency.exceptions.ClientResponseException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@Component
@RequiredArgsConstructor
public class AmadeusFlightSearch {
    private final AmadeusFlightSearchConfig amadeusFlightSearchConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(AmadeusFlightSearch.class);
    private final RestTemplate restTemplate;

    private AccessTokenResponse getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", amadeusFlightSearchConfig.getClientId());
        body.add("grant_type", "client_credentials");
        body.add("client_secret", amadeusFlightSearchConfig.getClientSecret());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        URI uri = UriComponentsBuilder.fromHttpUrl(amadeusFlightSearchConfig.getAmadeusEndpoint())
                .build()
                .toUri();
        try {
            return restTemplate.postForObject(uri, requestEntity, AccessTokenResponse.class);
        } catch (HttpClientErrorException ex) {
            throw new ClientResponseException("Something went wrong");
        }
    }

    public AmadeusFlight getAvailableFlights(Trip trip) {
        AccessTokenResponse accessTokenResponse = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessTokenResponse.getAccessToken());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(accessTokenResponse.getAccessToken());
        URI uri = UriComponentsBuilder.fromHttpUrl("https://test.api.amadeus.com/v2/shopping/flight-offers")
                .queryParam("originLocationCode", trip.getOriginIataCode())
                .queryParam("destinationLocationCode", trip.getDestinationsIataCode())
                .queryParam("departureDate", java.time.LocalDate.now().toString())
                .queryParam("adults", "1")
                .queryParam("max", "2")
                .build()
                .encode()
                .toUri();

        try {
            ResponseEntity<AmadeusFlight> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, AmadeusFlight.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new AmadeusFlight();
        }
    }
}