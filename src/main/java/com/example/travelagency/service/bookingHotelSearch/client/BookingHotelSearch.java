package com.example.travelagency.service.bookingHotelSearch.client;

import com.example.travelagency.exceptions.PassedDateException;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.model.dto.bookingModel.BookingAvailableHotelsInCity;

import com.example.travelagency.service.bookingHotelSearch.config.BookingHotelSearchConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class BookingHotelSearch {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingHotelSearch.class);
    private final BookingHotelSearchConfig bookingHotelSearchConfig;

    public List<BookingAvailableHotelsInCity> getAvailableHotels(String destination) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", bookingHotelSearchConfig.getBookingApiKey());
        headers.set("X-RapidAPI-Host", bookingHotelSearchConfig.getBookingApiHost());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder.fromHttpUrl(bookingHotelSearchConfig.getBookingDestinationEndpointUrl())
                .queryParam("name", destination)
                .queryParam("locale", "en-gb")
                .build()
                .encode()
                .toUri();
        System.out.println(uri);
        try {
            ResponseEntity<BookingAvailableHotelsInCity[]> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, BookingAvailableHotelsInCity[].class);
            return Arrays.stream(Objects.requireNonNull(responseEntity.getBody())).toList();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public HotelInfo getHotelsByCoordinates(BookingAvailableHotelsInCity availableHotelsInCity, BookingHotelRequest bookingHotelRequest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", bookingHotelSearchConfig.getBookingApiKey());
        headers.set("X-RapidAPI-Host", bookingHotelSearchConfig.getBookingApiHost());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder.fromHttpUrl(bookingHotelSearchConfig.getBookingSearchByCoordinatesEndpointUrl())
                .queryParam("units", "metric")
                .queryParam("room_number", "1")
                .queryParam("longitude", availableHotelsInCity.getLongitude())
                .queryParam("latitude", availableHotelsInCity.getLatitude())
                .queryParam("filter_by_currency", "USD")
                .queryParam("order_by", "popularity")
                .queryParam("locale", "en-gb")
                .queryParam("checkout_date", bookingHotelRequest.getCheckOutDate())
                .queryParam("adults_number", bookingHotelRequest.getAdultsNumber())
                .queryParam("checkin_date", bookingHotelRequest.getCheckInDate())
                .build()
                .encode()
                .toUri();
        System.out.println(uri);
        try {
            ResponseEntity<HotelInfo> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, HotelInfo.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            if (e.getMessage().contains("already passed")) {
                throw new PassedDateException("The date has already passed");
            }
            return new HotelInfo();
        }

    }
}
