package com.example.travelagency.mapper;

import com.example.travelagency.model.persistence.BookedTrip;
import com.example.travelagency.model.dto.BookTripDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookTripMapper {
    public BookTripDto mapToBookTripDto(BookedTrip bookedTrip) {
        return  BookTripDto.builder()
                .username(bookedTrip.getUser().getUsername())
                .userId(bookedTrip.getUser().getId())
                .flight(bookedTrip.getFlight())
                .hotel(bookedTrip.getHotel())
                .build();
    }
    public List<BookTripDto> mapToBookTripDtoList(List<BookedTrip> allBookedTrips) {
        return allBookedTrips.stream().map(this::mapToBookTripDto).collect(Collectors.toList());
    }
}
