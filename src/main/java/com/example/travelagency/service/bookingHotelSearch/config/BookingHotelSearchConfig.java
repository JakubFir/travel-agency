package com.example.travelagency.service.bookingHotelSearch.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BookingHotelSearchConfig {
    @Value("${booking.api.key}")
    private String bookingApiKey;
    @Value("${booking.api.host}")
    private String bookingApiHost;
    @Value("${booking.destination.endpoint.url}")
    private String bookingDestinationEndpointUrl;
    @Value("${airbnb.search.by.place.endpoint.url}")
    private String bookingSearchByCoordinatesEndpointUrl;


}
