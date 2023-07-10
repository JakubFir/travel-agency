package com.example.travelagency.service;

import com.example.travelagency.mapper.FlightMapper;
import com.example.travelagency.mapper.HotelMapper;
import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.model.dto.amadeusModel.AmadeusFlight;
import com.example.travelagency.model.dto.amadeusModel.FlightInfo;
import com.example.travelagency.model.dto.amadeusModel.FlightPriceInfo;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.model.dto.bookingModel.HotelModel;
import com.example.travelagency.model.persistence.*;
import com.example.travelagency.repository.*;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingTripServiceTest {
    @Mock
    private BookedTripRepository bookedTripRepository;
    @Mock
    private FlightMapper flightMapper;
    @Mock
    private TripRepository tripRepository;
    @Mock
    private AmadeusFlightSearch amadeusFlightSearch;
    @Mock
    private HotelMapper hotelMapper;
    @Mock
    private BookingHotelService bookingHotelService;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private HotelRepository hotelRepository;

    private BookingTripService bookingTripService;

    @BeforeEach
    void setUp() {
        bookingTripService = new BookingTripService(
                bookedTripRepository, flightMapper,
                tripRepository, amadeusFlightSearch,
                hotelMapper, bookingHotelService,
                flightRepository, userRepository,
                hotelRepository);
    }

    @Test
    void bookTrip() {
        //Given
        Trip trip = new Trip("Test", "test", "test");
        User user = new User("test", "test", "test", "test","Paris","PAR", Role.USER);
        Hotel hotel = new Hotel(1L, "test", "test", 2, "test", "test", true);
        Flight flight = new Flight("test", "test");
        HotelModel hotelModel = new HotelModel("test", "test", 1L, 2, "test", "test", true);
        List<BookedTrip> list = new ArrayList<>();
        user.setBookedTrips(list);
        FlightInfo flightInfo = new FlightInfo("test", 1L, new ArrayList<>(), new FlightPriceInfo(new BigDecimal(1)));
        BookingHotelRequest bookingHotelRequest = new BookingHotelRequest("Test", "test", "test", "test", 1, 1L);
        BookingRequest bookingRequest = new BookingRequest(1L, 1L, bookingHotelRequest);

        when(tripRepository.findById(any())).thenReturn(Optional.of(trip));
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(bookingHotelService.getHotelsByCoordinates(any(BookingHotelRequest.class)))
                .thenReturn(new HotelInfo(Collections.singletonList(hotelModel)));
        when(hotelMapper.mapToHotel(hotelModel)).thenReturn(hotel);

        when(amadeusFlightSearch.getAvailableFlights(any(Trip.class),any()))
                .thenReturn(new AmadeusFlight(Collections.singletonList(flightInfo)));
        when(flightMapper.mapFlightInfo(flightInfo)).thenReturn(flight);

        //When
        bookingTripService.bookTrip(bookingRequest, 1L);

        //Then
        ArgumentCaptor<BookedTrip> bookedTripArgumentCaptor = ArgumentCaptor.forClass(BookedTrip.class);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(bookedTripRepository).save(bookedTripArgumentCaptor.capture());
        verify(userRepository).save(userArgumentCaptor.capture());
        BookedTrip bookedTrip = bookedTripArgumentCaptor.getValue();

        assertThat(bookedTrip.getUser().getName()).isEqualTo(user.getName());
        assertThat(bookedTrip.getHotel().getHotelId()).isEqualTo(hotelModel.getHotelId());
        assertThat(bookedTrip.getFlight().getArrival()).isEqualTo(flight.getArrival());


    }

    @Test
    void getAllBookedTrips() {
        //Given
        List<BookedTrip> list = new ArrayList<>();
        User user = new User("test", "test", "test", "test","Paris","PAR", Role.USER);
        user.setBookedTrips(list);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //When
        List<BookedTrip> result = bookingTripService.getAllBookedTrips(1L);

        //Then
        assertThat(result).isEqualTo(list);
    }
}