package com.example.travelagency.mapper;

import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.model.dto.TripDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripMapper {
    public TripDto mapToTripDto(Trip trip) {
        return TripDto.builder()
                .tripId(trip.getId())
                .destination(trip.getDestination())
                .description(trip.getDescription())
                .destinationsIataCode(trip.getDestinationsIataCode())
                .build();
    }

    public List<TripDto> mapToTripDtoList(List<Trip> listOfTrips) {
        return listOfTrips.stream().map(this::mapToTripDto).collect(Collectors.toList());
    }
}
