package com.example.travelagency.service;

import com.example.travelagency.domain.BookTrip;
import com.example.travelagency.domain.Flight;
import com.example.travelagency.domain.Trip;
import com.example.travelagency.domain.User;
import com.example.travelagency.exceptions.FlightNotFoundException;
import com.example.travelagency.exceptions.TripNotFoundException;
import com.example.travelagency.mapper.FlightMapper;
import com.example.travelagency.model.AmadeusFlight;
import com.example.travelagency.model.FlightInfo;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.repository.BookTripRepository;
import com.example.travelagency.repository.FlightRepository;
import com.example.travelagency.repository.TripRepository;
import com.example.travelagency.repository.UserRepository;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookTripService {
    private final BookTripRepository bookTripRepository;
    private final FlightMapper flightMapper;
    private final TripRepository tripRepository;
    private final AmadeusFlightSearch amadeusFlightSearch;

    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public void bookTrip(BookingRequest bookingRequest, Long userId) {
        Trip tripToBook = tripRepository.findById(bookingRequest.getTripId())
                .orElseThrow(() -> new TripNotFoundException("Trip with given id doesn't exists"));
        User bookingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        Flight getChosenFlightForTrip = getFlight(bookingRequest.getFlightId(), tripToBook);

        flightRepository.save(getChosenFlightForTrip);

        BookTrip bookTrip = new BookTrip();
        bookTrip.setFlight(getChosenFlightForTrip);
        bookTrip.setUser(bookingUser);
        bookingUser.getBookedTrips().add(bookTrip);

        bookTripRepository.save(bookTrip);
        userRepository.save(bookingUser);
    }

    private Flight getFlight(Long flightId, Trip tripToBook) {
        AmadeusFlight amadeusFlight = amadeusFlightSearch.getFlight(tripToBook);
        FlightInfo flightInfo = amadeusFlight.getAvailableFlights()
                .stream()
                .filter(flight -> flight.getId().equals(flightId))
                .findFirst()
                .orElseThrow(() -> new FlightNotFoundException("Flight with given id doesn't exists"));
        return flightMapper.mapFlightInfo(flightInfo);
    }

    public List<BookTrip> getAllBookedTrips(Long userId) {
        User userToGetBookedTrips = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userToGetBookedTrips.getBookedTrips();
    }
}
