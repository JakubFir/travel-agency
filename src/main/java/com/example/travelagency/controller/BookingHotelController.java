package com.example.travelagency.controller;

import com.example.travelagency.model.dto.bookingModel.BookingAvailableHotelsInCity;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.service.BookingHotelService;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "hotels")
public class BookingHotelController {

    private final BookingHotelSearch bookingHotelSearch;
    private final BookingHotelService bookingHotelService;

    @GetMapping(path = "/{destination}")
    public List<BookingAvailableHotelsInCity> getHotels(@PathVariable String destination) {
        return bookingHotelSearch.getAvailableHotels(destination);
    }

    @PostMapping
    public HotelInfo getHotelsByCoordinates(@RequestBody BookingHotelRequest bookingHotelRequest) {
        return bookingHotelService.getHotelsByCoordinates(bookingHotelRequest);
    }

}
