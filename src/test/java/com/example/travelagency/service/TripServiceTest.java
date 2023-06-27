package com.example.travelagency.service;

import com.example.travelagency.mapper.TripMapper;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.model.persistence.TripInfo;
import com.example.travelagency.model.dto.amadeusModel.AmadeusFlight;
import com.example.travelagency.model.dto.amadeusModel.FlightInfo;
import com.example.travelagency.model.dto.bookingModel.BookingAvailableHotelsInCity;
import com.example.travelagency.repository.NewsLetterRepository;
import com.example.travelagency.repository.SubscriberRepository;
import com.example.travelagency.repository.TripRepository;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import com.example.travelagency.service.observer.ObservableImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {
    @Mock
    private TripRepository tripRepository;
    @Mock
    private AmadeusFlightSearch amadeusFlightSearch;
    @Mock
    private BookingHotelSearch bookingHotelSearch;
    @Mock
    private NewsLetterRepository newsLetterRepository;
    @Mock
    private TripMapper tripMapper;
    private ObservableImpl observable;

    private TripService tripService;

    @BeforeEach
    void setUp() {
        tripService = new TripService(tripRepository, amadeusFlightSearch, bookingHotelSearch, newsLetterRepository, tripMapper, observable);
    }

    @Test
    void addTrip() {
        //Given

        Trip trip = new Trip("test", "test", "test", "test", "test");

        //When
        tripService.addTrip(trip);

        //Then
        ArgumentCaptor<Trip> tripArgumentCaptor = ArgumentCaptor.forClass(Trip.class);
        verify(tripRepository).save(tripArgumentCaptor.capture());
    }

    @Test
    void getListOfTrips() {
        //Given
        List<Trip> list = new ArrayList<>();
        Trip trip = new Trip("test", "test", "test", "test", "test");
        list.add(trip);
        when(tripRepository.findAll()).thenReturn(list);

        //When
        List<Trip> result = tripService.getListOfTrips();

        //Then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getDescription()).isEqualTo(list.get(0).getDescription());
    }

    @Test
    void getTripInfo() {
        //Given
        Trip trip = new Trip("test", "test", "test", "test", "test");
        List<FlightInfo> flightInfos = new ArrayList<>();
        AmadeusFlight amadeusFlight = new AmadeusFlight(flightInfos);
        List<BookingAvailableHotelsInCity> bookingAvailableHotelsInCities = new ArrayList<>();
        TripInfo tripInfo = new TripInfo(trip, flightInfos, bookingAvailableHotelsInCities);
        when(tripRepository.findById(1L)).thenReturn(Optional.of(trip));
        when(amadeusFlightSearch.getAvailableFlights(trip)).thenReturn(amadeusFlight);
        when(bookingHotelSearch.getAvailableHotels(trip.getDestination())).thenReturn(bookingAvailableHotelsInCities);

        //When
        TripInfo tripInfo1 = tripService.getTripInfo(1L);

        //Then
        assertThat(tripInfo1).isEqualTo(tripInfo);
        assertThat(tripInfo1.getTrip()).isEqualTo(tripInfo.getTrip());


    }
}