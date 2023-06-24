package com.example.travelagency.service;

import com.example.travelagency.domain.*;
import com.example.travelagency.exceptions.FlightNotFoundException;
import com.example.travelagency.exceptions.TripNotFoundException;
import com.example.travelagency.mapper.FlightMapper;
import com.example.travelagency.mapper.HotelMapper;
import com.example.travelagency.model.bookingModel.HotelModel;
import com.example.travelagency.model.bookingModel.HotelInfo;
import com.example.travelagency.model.amadeusModel.AmadeusFlight;
import com.example.travelagency.model.amadeusModel.FlightInfo;
import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.repository.*;
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
    private final HotelMapper hotelMapper;
    private final BookingHotelService bookingHotelService;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    public void bookTrip(BookingRequest bookingRequest, Long userId) {
        Trip tripToBook = tripRepository.findById(bookingRequest.getTripId())
                .orElseThrow(() -> new TripNotFoundException("Trip with given id doesn't exists"));
        User bookingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        Flight getChosenFlightForTrip = getFlight(bookingRequest.getFlightId(), tripToBook);
        flightRepository.save(getChosenFlightForTrip);

        Hotel getChosenHotelForTripModel = getHotel(bookingRequest.getBookingHotelRequest(),bookingRequest.getHotelId());
        hotelRepository.save(getChosenHotelForTripModel);

        BookTrip bookTrip = new BookTrip();
        bookTrip.setFlight(getChosenFlightForTrip);
        bookTrip.setUser(bookingUser);
        bookTrip.setHotel(getChosenHotelForTripModel);
        bookingUser.getBookedTrips().add(bookTrip);

        bookTripRepository.save(bookTrip);
        userRepository.save(bookingUser);
    }

    private Hotel getHotel(BookingHotelRequest bookingHotelRequest, Long hotelId) {
        HotelInfo hotelInfo = bookingHotelService.getHotelsByCoordinates(bookingHotelRequest);
        HotelModel hotelModel = hotelInfo.getResult()
                .stream()
                .filter(hotelModel1 -> hotelModel1.getHotel_id().equals(hotelId))
                .findFirst()
                .orElseThrow();
        return hotelMapper.mapToHotel(hotelModel);
    }

    private Flight getFlight(Long flightId, Trip tripToBook) {
        AmadeusFlight amadeusFlight = amadeusFlightSearch.getAvailableFlights(tripToBook);
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
