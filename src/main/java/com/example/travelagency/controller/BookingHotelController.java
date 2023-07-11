package com.example.travelagency.controller;

import com.example.travelagency.model.dto.bookingModel.BookingAvailableHotelsInCity;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.service.BookingHotelService;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "hotels")
public class BookingHotelController {

    private final BookingHotelSearch bookingHotelSearch;
    private final BookingHotelService bookingHotelService;

    @GetMapping(path = "/{destination}")
    public ResponseEntity<List<BookingAvailableHotelsInCity>> getHotels(@PathVariable String destination) {
        return ResponseEntity.ok(bookingHotelSearch.getAvailableHotels(destination));
    }

    @PostMapping
    public ResponseEntity<HotelInfo> getHotelsByCoordinates(@RequestBody BookingHotelRequest bookingHotelRequest) {
        return ResponseEntity.ok(bookingHotelService.getHotelsByPlaceName(bookingHotelRequest));
    }

}
