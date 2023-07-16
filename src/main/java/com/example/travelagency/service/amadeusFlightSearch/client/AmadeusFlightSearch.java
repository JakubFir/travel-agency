package com.example.travelagency.service.amadeusFlightSearch.client;


import com.example.travelagency.model.dto.FlightRequest;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.model.persistence.User;
import com.example.travelagency.repository.TripRepository;
import com.example.travelagency.repository.UserRepository;
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
    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AmadeusFlightSearch.class);
    private final RestTemplate restTemplate;
    private final TripRepository tripRepository;

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

    public AmadeusFlight getTripAvailableFlights(Long tripId, Long userId, FlightRequest flightRequest) {
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        AccessTokenResponse accessTokenResponse = getAccessToken();

        String originIataCode = user.getOriginIataCode();
        String destinationIataCode = trip.getDestinationsIataCode();
        String departureDate = flightRequest.getDepartureDate();

        return availableFlights(originIataCode, destinationIataCode, departureDate, accessTokenResponse);
    }
    public AmadeusFlight getReturnAvailableFlights(Long tripId, Long userId, FlightRequest flightRequest) {
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        AccessTokenResponse accessTokenResponse = getAccessToken();

        String originIataCode = trip.getDestinationsIataCode();
        String destinationIataCode = user.getOriginIataCode();
        String departureDate = flightRequest.getDepartureDate();

        return availableFlights(originIataCode, destinationIataCode, departureDate, accessTokenResponse);
    }

    private AmadeusFlight availableFlights(String originIataCode, String destinationIataCode, String departureDate, AccessTokenResponse accessTokenResponse) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessTokenResponse.getAccessToken());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(accessTokenResponse.getAccessToken());
        URI uri = UriComponentsBuilder.fromHttpUrl("https://test.api.amadeus.com/v2/shopping/flight-offers")
                .queryParam("originLocationCode", originIataCode)
                .queryParam("destinationLocationCode", destinationIataCode)
                .queryParam("departureDate", departureDate)
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