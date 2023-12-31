package com.example.travelagency.controller;

import com.example.travelagency.mapper.BookedTripMapper;
import com.example.travelagency.model.dto.BookedTripDto;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.service.BookingTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "book")
@RequiredArgsConstructor
public class BookedTripController {
    private final BookingTripService bookingTripService;
    private final BookedTripMapper bookedTripMapper;


    @PostMapping(path = "{userId}")
    public ResponseEntity<Void> bookTrip(@RequestBody BookingRequest bookingRequest, @PathVariable Long userId) {
        bookingTripService.bookTrip(bookingRequest, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<BookedTripDto>> getBookedTrips(@PathVariable Long userId) {
        return ResponseEntity.ok(bookedTripMapper.mapToBookedTripDtoList(bookingTripService.getAllBookedTrips(userId)));
    }

    @DeleteMapping("{bookedTripId}")
    public ResponseEntity<Void> deleteBookedTrip(@PathVariable Long bookedTripId) {
        bookingTripService.deleteBookedTrip(bookedTripId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{tripId}/{userId}")
    public ResponseEntity<Void> updateBookedTrip(@RequestBody BookingRequest bookingRequest, @PathVariable Long tripId, @PathVariable Long userId) {
        bookingTripService.updateBookedTrip(bookingRequest, tripId, userId);
        return ResponseEntity.ok().build();
    }
}
