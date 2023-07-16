package com.example.travelagency.mapper;

import com.example.travelagency.model.persistence.BookedTrip;
import com.example.travelagency.model.dto.BookedTripDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookedTripMapper {
    public BookedTripDto mapToBookedTripDto(BookedTrip bookedTrip) {
        return  BookedTripDto.builder()
                .username(bookedTrip.getUser().getUsername())
                .userId(bookedTrip.getUser().getId())
                .flight(bookedTrip.getFlight())
                .hotel(bookedTrip.getHotel())
                .build();
    }
    public List<BookedTripDto> mapToBookedTripDtoList(List<BookedTrip> allBookedTrips) {
        return allBookedTrips.stream().map(this::mapToBookedTripDto).collect(Collectors.toList());
    }
}
