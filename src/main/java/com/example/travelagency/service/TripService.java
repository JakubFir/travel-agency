package com.example.travelagency.service;

import com.example.travelagency.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.amadeusFlightSearch.dto.AmadeusFlight;
import com.example.travelagency.amadeusFlightSearch.dto.OriginFlight;
import com.example.travelagency.domain.Trip;
import com.example.travelagency.dto.TripInfo;
import com.example.travelagency.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final AmadeusFlightSearch amadeusFlightSearch;

    public void addTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public List<Trip> getListOfTrips() {
        return tripRepository.findAll();
    }

    public TripInfo getTripInfo(Long tripId) {
        Trip tripToGetInformation = tripRepository.findById(tripId).orElseThrow();
        TripInfo tripInfo = new TripInfo();
        OriginFlight originFlight = new OriginFlight();

        originFlight.setOrigin(tripToGetInformation.getOrigin());
        originFlight.setDestination(tripToGetInformation.getDestinations());

        tripInfo.setTrip(tripToGetInformation);
        tripInfo.setListOfAvailableFlights(amadeusFlightSearch.getFlight(originFlight).getAvailableFlights());
        return tripInfo;
    }
}
