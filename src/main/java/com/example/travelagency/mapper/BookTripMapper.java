package com.example.travelagency.mapper;

import com.example.travelagency.domain.BookTrip;
import com.example.travelagency.model.dto.BookTripDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookTripMapper {
    public BookTripDto mapToBookTripDto(BookTrip bookTrip) {
        return  BookTripDto.builder()
                .username(bookTrip.getUser().getUsername())
                .userId(bookTrip.getUser().getId())
                .flight(bookTrip.getFlight())
                .hotel(bookTrip.getHotel())
                .build();
    }
    public List<BookTripDto> mapToBookTripDtoList(List<BookTrip> allBookedTrips) {
        return allBookedTrips.stream().map(this::mapToBookTripDto).collect(Collectors.toList());
    }
}
