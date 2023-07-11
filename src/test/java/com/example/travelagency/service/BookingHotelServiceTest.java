package com.example.travelagency.service;

import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.model.dto.bookingModel.BookingAvailableHotelsInCity;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingHotelServiceTest {
    @Mock
    private BookingHotelSearch bookingHotelSearch;

    private BookingHotelService bookingHotelService;

    @BeforeEach
    void setUp() {
        bookingHotelService = new BookingHotelService(bookingHotelSearch);
    }

    @Test
    void getHotelsByCoordinates() {
        //Given
        HotelInfo hotelInfo = new HotelInfo(new ArrayList<>());
        BookingAvailableHotelsInCity bookingAvailableHotelsInCity =
                mock(BookingAvailableHotelsInCity.class);
        BookingHotelRequest bookingHotelRequest = mock(BookingHotelRequest.class);

        when(bookingHotelRequest.getDestination()).thenReturn("test");
        when(bookingHotelRequest.getPlaceName()).thenReturn("test");
        when(bookingHotelSearch.getAvailableHotels("test")).thenReturn(List.of(bookingAvailableHotelsInCity));
        when(bookingAvailableHotelsInCity.getName()).thenReturn("test");
        when(bookingHotelSearch.getHotelsByCoordinates(
                eq(bookingAvailableHotelsInCity), eq(bookingHotelRequest)))
                .thenReturn(hotelInfo);
        // When
        HotelInfo result = bookingHotelService.getHotelsByPlaceName(bookingHotelRequest);

        // Then
        assertThat(result).isEqualTo(hotelInfo);

    }
}