package com.example.travelagency.service;

import com.example.travelagency.domain.Subscriber;
import com.example.travelagency.repository.SubscriberRepository;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.domain.Trip;
import com.example.travelagency.domain.TripInfo;
import com.example.travelagency.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final AmadeusFlightSearch amadeusFlightSearch;
    private final BookingHotelSearch bookingHotelSearch;
    private final SubscriberRepository subscriberRepository;


    public void addTrip(Trip trip) {
        List<Subscriber> list = subscriberRepository.findAll();
        trip.setSubscriberList(list);
        tripRepository.save(trip);
        trip.notifyObs(trip);
    }

    public List<Trip> getListOfTrips() {
        return tripRepository.findAll();
    }

    public TripInfo getTripInfo(Long tripId) {
        Trip tripToGetInformation = tripRepository.findById(tripId).orElseThrow();
        TripInfo tripInfo = new TripInfo();

        tripInfo.setTrip(tripToGetInformation);
        tripInfo.setListOfAvailableFlights(amadeusFlightSearch.getAvailableFlights(tripToGetInformation).getAvailableFlights());
        tripInfo.setAvailableHotelsInCities(bookingHotelSearch.getAvailableHotels(tripToGetInformation.getDestinationsIataCode()));
        return tripInfo;
    }
}
