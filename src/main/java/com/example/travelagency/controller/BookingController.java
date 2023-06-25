package com.example.travelagency.controller;

import com.example.travelagency.model.bookingModel.BookingAvailableHotelsInCity;
import com.example.travelagency.model.bookingModel.HotelInfo;
import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.service.BookingHotelService;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "hotels")
public class BookingController {

    private final BookingHotelSearch bookingHotelSearch;
    private final BookingHotelService bookingHotelService;

    @GetMapping(path = "/{destination}")
    public List<BookingAvailableHotelsInCity> getHotels(@PathVariable String destination) {
        return bookingHotelSearch.getAvailableHotels(destination);
    }

    @GetMapping
    public HotelInfo getHotelsByCoordinates(@RequestBody BookingHotelRequest bookingHotelRequest) {
        return bookingHotelService.getHotelsByCoordinates(bookingHotelRequest);
    }

}
