package com.example.travelagency.service;

import com.example.travelagency.mapper.TripMapper;
import com.example.travelagency.model.dto.FlightRequest;
import com.example.travelagency.model.persistence.Newsletter;
import com.example.travelagency.repository.NewsLetterRepository;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.model.persistence.TripInfo;
import com.example.travelagency.repository.TripRepository;
import com.example.travelagency.service.observer.NewsLetterObservable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final AmadeusFlightSearch amadeusFlightSearch;
    private final BookingHotelSearch bookingHotelSearch;
    private final NewsLetterRepository newsLetterRepository;
    private final TripMapper tripMapper;
    private final NewsLetterObservable newsLetterObservable;


    public void addTrip(Trip trip) {
        tripRepository.save(trip);
        List<Newsletter> newsletterList = newsLetterRepository.findAll();
        for (Newsletter newsLetter : newsletterList) {
            if (newsLetter.getNewsletterTitle().equals("New Trip")) {
                newsLetterObservable.notifyObs(tripMapper.mapToTripDto(trip), newsLetter.getObserverList());
            }
        }
    }

    public List<Trip> getListOfTrips() {
        return tripRepository.findAll();
    }

    public TripInfo getTripInfo(Long tripId, Long userId) {
        Trip tripToGetInformation = tripRepository.findById(tripId).orElseThrow();
        TripInfo tripInfo = new TripInfo();
        FlightRequest defaultFlightRequest = new FlightRequest(java.time.LocalDate.now().plusDays(1).toString());
        tripInfo.setTrip(tripToGetInformation);
        tripInfo.setListOfAvailableFlights(amadeusFlightSearch.getTripAvailableFlights(tripId, userId, defaultFlightRequest).getAvailableFlights());
        tripInfo.setAvailableHotelsInCities(bookingHotelSearch.getAvailableHotels(tripToGetInformation.getDestinationsIataCode()));
        return tripInfo;
    }
}
