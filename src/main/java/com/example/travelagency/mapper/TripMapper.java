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
                .destination(trip.getDestination())
                .origin(trip.getOrigin())
                .description(trip.getDescription())
                .originIataCode(trip.getOriginIataCode())
                .destinationsIataCode(trip.getDestinationsIataCode())
                .build();
    }

    public List<TripDto> mapToTripDtoList(List<Trip> listOfTrips) {
        return listOfTrips.stream().map(this::mapToTripDto).collect(Collectors.toList());
    }

    public Trip mapToTrip(TripDto trip) {
        return new Trip(
                trip.getOrigin(),
                trip.getOriginIataCode(),
                trip.getDestinationsIataCode(),
                trip.getDestination(),
                trip.getDescription());
    }
}
