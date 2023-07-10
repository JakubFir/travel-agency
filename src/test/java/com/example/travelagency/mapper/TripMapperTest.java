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
        Trip trip = new Trip("LHR", "London", "JFK");

        // When
        TripDto tripDto = tripMapper.mapToTripDto(trip);

        // Then
        assertEquals("London", tripDto.getDestination());
        assertEquals("JFK", tripDto.getDescription());
    }

    @Test
    void mapToTripDtoList() {
        // Given
        List<Trip> trips = new ArrayList<>();
        Trip trip1 = new Trip("LHR", "London", "JFK");
        trips.add(trip1);

        Trip trip2 = new Trip("CDG", "Los Angeles", "LAX");
        trips.add(trip2);

        // When
        List<TripDto> tripDtoList = tripMapper.mapToTripDtoList(trips);

        // Then
        assertEquals(2, tripDtoList.size());

        TripDto tripDto1 = tripDtoList.get(0);
        assertEquals("London", tripDto1.getDestination());
        assertEquals("JFK", tripDto1.getDescription());

        TripDto tripDto2 = tripDtoList.get(1);
        assertEquals("Los Angeles", tripDto2.getDestination());
        assertEquals("LAX", tripDto2.getDescription());
    }
}