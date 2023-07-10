package com.example.travelagency.controller;

import com.example.travelagency.mapper.BookTripMapper;
import com.example.travelagency.model.dto.BookedTripDto;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.model.persistence.BookedTrip;
import com.example.travelagency.service.BookingTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "book")
@RequiredArgsConstructor
public class BookedTripController {
    private final BookingTripService bookingTripService;
    private final BookTripMapper bookTripMapper;

    @PostMapping(path = "{userId}")
    public void bookTrip(@RequestBody BookingRequest bookingRequest, @PathVariable Long userId){
        bookingTripService.bookTrip(bookingRequest, userId);
    }
    @GetMapping("{userId}")
    public List<BookedTripDto> getBookedTrips(@PathVariable Long userId){
        return bookTripMapper.mapToBookTripDtoList(bookingTripService.getAllBookedTrips(userId));
    }
    @DeleteMapping("{bookedTripId}")
    public void deleteBookedTrip(@PathVariable Long bookedTripId){
        bookingTripService.deleteBookedTrip(bookedTripId);
    }
    @PutMapping("{tripId}/{userId}")
    public void updateBookedTrip(@RequestBody BookingRequest bookingRequest, @PathVariable Long tripId,@PathVariable Long userId){
        bookingTripService.updateBookedTrip(bookingRequest,tripId,userId);
    }
}
