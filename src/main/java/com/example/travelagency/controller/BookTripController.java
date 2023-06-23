package com.example.travelagency.controller;

import com.example.travelagency.domain.BookTrip;
import com.example.travelagency.mapper.BookTripMapper;
import com.example.travelagency.model.dto.BookTripDto;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.service.BookTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "book")
@RequiredArgsConstructor
public class BookTripController {
    private final BookTripService bookTripService;
    private final BookTripMapper bookTripMapper;

    @PostMapping(path = "{userId}")
    public void bookTrip(@RequestBody BookingRequest bookingRequest, @PathVariable Long userId){
        bookTripService.bookTrip(bookingRequest, userId);
    }
    @GetMapping("{userId}")
    public List<BookTripDto> getBookedTrips(@PathVariable Long userId){
        return bookTripMapper.mapToBookTripDtiList(bookTripService.getAllBookedTrips(userId));
    }

}
