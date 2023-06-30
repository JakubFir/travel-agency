package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.amadeusModel.*;
import com.example.travelagency.model.persistence.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.Segment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightMapperTest {
    private FlightMapper flightMapper;

    @BeforeEach
    void setUp() {
        flightMapper = new FlightMapper();
    }

    @Test
    void mapFlightInfo() {
        // Given
        FlightInfo flightInfo = new FlightInfo();
        flightInfo.setType("flight");
        flightInfo.setId(1L);

        Departure departure = new Departure(); //
        departure.setIataCode("LHR");

        Arrival arrival = new Arrival();
        arrival.setIataCode("JFK");

        Intineraries itineraries = new Intineraries();

        List<Segments> segments = new ArrayList<>();
        Segments segment = new Segments();
        segment.setDeparture(departure);
        segment.setArrival(arrival);
        segments.add(segment);

        itineraries.setSegments(segments);
        List<Intineraries> itinerariesList = new ArrayList<>();
        itinerariesList.add(itineraries);
        flightInfo.setItineraries(itinerariesList);

        // When
        Flight flight = flightMapper.mapFlightInfo(flightInfo);

        // Then
        assertEquals("LHR", flight.getDeparture());
        assertEquals("JFK", flight.getArrival());
    }
}