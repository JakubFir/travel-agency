package com.example.travelagency.controller;

import com.example.travelagency.mapper.BookTripMapper;
import com.example.travelagency.model.dto.BookedTripDto;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.model.persistence.BookedTrip;
import com.example.travelagency.service.BookingTripService;
import com.example.travelagency.validator.BookingRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "book")
@RequiredArgsConstructor
public class BookedTripController {
    private final BookingTripService bookingTripService;
    private final BookTripMapper bookTripMapper;


    @PostMapping(path = "{userId}")
    public ResponseEntity<Void> bookTrip(@RequestBody BookingRequest bookingRequest, @PathVariable Long userId) {
        bookingTripService.bookTrip(bookingRequest, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<BookedTripDto>> getBookedTrips(@PathVariable Long userId) {
        return ResponseEntity.ok(bookTripMapper.mapToBookTripDtoList(bookingTripService.getAllBookedTrips(userId)));
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
