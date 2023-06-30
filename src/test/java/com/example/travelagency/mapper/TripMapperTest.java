package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TripMapperTest {
    private TripMapper tripMapper;

    @BeforeEach
    void setUp() {
        tripMapper = new TripMapper();
    }

    @Test
    void mapToTripDto() {
        // Given
        Trip trip = new Trip("London", "LHR", "JFK", "New York", "Trip description");

        // When
        TripDto tripDto = tripMapper.mapToTripDto(trip);

        // Then
        assertEquals("London", tripDto.getOrigin());
        assertEquals("New York", tripDto.getDestination());
        assertEquals("Trip description", tripDto.getDescription());
    }

    @Test
    void mapToTripDtoList() {
        // Given
        List<Trip> trips = new ArrayList<>();
        Trip trip1 = new Trip("London", "LHR", "JFK", "New York", "Trip description 1");
        trips.add(trip1);

        Trip trip2 = new Trip("Paris", "CDG", "LAX", "Los Angeles", "Trip description 2");
        trips.add(trip2);

        // When
        List<TripDto> tripDtoList = tripMapper.mapToTripDtoList(trips);

        // Then
        assertEquals(2, tripDtoList.size());

        TripDto tripDto1 = tripDtoList.get(0);
        assertEquals("London", tripDto1.getOrigin());
        assertEquals("New York", tripDto1.getDestination());
        assertEquals("Trip description 1", tripDto1.getDescription());

        TripDto tripDto2 = tripDtoList.get(1);
        assertEquals("Paris", tripDto2.getOrigin());
        assertEquals("Los Angeles", tripDto2.getDestination());
        assertEquals("Trip description 2", tripDto2.getDescription());
    }
}