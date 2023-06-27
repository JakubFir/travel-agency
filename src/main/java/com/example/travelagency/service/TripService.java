package com.example.travelagency.service;

import com.example.travelagency.mapper.TripMapper;
import com.example.travelagency.model.persistence.NewsLetter;
import com.example.travelagency.repository.NewsLetterRepository;
import com.example.travelagency.service.bookingHotelSearch.client.BookingHotelSearch;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.model.persistence.TripInfo;
import com.example.travelagency.repository.TripRepository;
import com.example.travelagency.service.observer.ObservableImpl;
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
    private final ObservableImpl observableImpl;


    public void addTrip(Trip trip) {
        tripRepository.save(trip);
        List<NewsLetter> newsLetterList = newsLetterRepository.findAll();
        for(NewsLetter newsLetter : newsLetterList){
            if (newsLetter.getNewsLetterTitle().equals("New Trip")){
                observableImpl.notifyObs(tripMapper.mapToTripDto(trip),newsLetter.getSubscriberList());
            }
        }
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
