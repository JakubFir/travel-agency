package com.example.travelagency.service;

import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookingHotelService {

    private final BookingHotelSearch bookingHotelSearch;

    public HotelInfo getHotelsByPlaceName(BookingHotelRequest bookingHotelRequest) {
        return bookingHotelSearch.getAvailableHotels(bookingHotelRequest.getDestination())
                .stream()
                .filter(hotel -> hotel.getName().equals(bookingHotelRequest.getPlaceName()))
                .findFirst()
                .map(hotel -> bookingHotelSearch.getHotelsByCoordinates(hotel, bookingHotelRequest))
                .orElse(new HotelInfo());
    }
}
