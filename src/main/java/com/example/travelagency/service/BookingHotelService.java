package com.example.travelagency.service;

import com.example.travelagency.model.dto.bookingModel.BookingAvailableHotelsInCity;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingHotelService {

    private final BookingHotelSearch bookingHotelSearch;

    public HotelInfo getHotelsByCoordinates(BookingHotelRequest bookingHotelRequest) {
        List<BookingAvailableHotelsInCity> availableHotels = bookingHotelSearch.getAvailableHotels(bookingHotelRequest.getDestination());
        for (BookingAvailableHotelsInCity hotel : availableHotels) {
            if (hotel.getName().equals(bookingHotelRequest.getPlaceName())) {
                return bookingHotelSearch.getHotelsByCoordinates(hotel, bookingHotelRequest);
            }
        }
        return new HotelInfo();
    }
}
