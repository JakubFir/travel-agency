package com.example.travelagency.mapper;

import com.example.travelagency.domain.Trip;
import com.example.travelagency.dto.TripDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TripMapper {
    public TripDto mapToTripDto(Trip trip) {
        return TripDto.builder()
                .destinations(trip.getDestinations())
                .origin(trip.getOrigin())
                .price(trip.getPrice())
                .build();

    }

    public List<TripDto> mapToTripDtoList(List<Trip> listOfTrips) {
        return listOfTrips.stream().map(this::mapToTripDto).collect(Collectors.toList());
    }
}
