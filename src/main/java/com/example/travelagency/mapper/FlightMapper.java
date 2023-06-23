package com.example.travelagency.mapper;

import com.example.travelagency.domain.Flight;
import com.example.travelagency.model.FlightInfo;
import org.springframework.stereotype.Service;

@Service
public class FlightMapper {
    public Flight mapFlightInfo(FlightInfo flightInfo) {
        return new Flight(
                flightInfo.getItineraries().get(0).getSegments().get(0).getDeparture().getIataCode(),
                flightInfo.getItineraries().get(0).getSegments().get(0).getArrival().getIataCode());
    }
}
