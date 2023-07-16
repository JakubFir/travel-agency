package com.example.travelagency.service;

import com.example.travelagency.exceptions.FlightNotFoundException;
import com.example.travelagency.exceptions.HotelNotFoundException;
import com.example.travelagency.exceptions.TripNotFoundException;
import com.example.travelagency.mapper.FlightMapper;
import com.example.travelagency.mapper.HotelMapper;
import com.example.travelagency.model.dto.FlightRequest;
import com.example.travelagency.model.dto.bookingModel.HotelInfo;
import com.example.travelagency.model.dto.amadeusModel.AmadeusFlight;
import com.example.travelagency.model.dto.BookingHotelRequest;
import com.example.travelagency.model.dto.BookingRequest;
import com.example.travelagency.model.persistence.*;
import com.example.travelagency.repository.*;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.validator.BookingRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingTripService {
    private final BookedTripRepository bookedTripRepository;
    private final FlightMapper flightMapper;
    private final TripRepository tripRepository;
    private final AmadeusFlightSearch amadeusFlightSearch;
    private final HotelMapper hotelMapper;
    private final BookingHotelService bookingHotelService;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final BookingRequestValidator bookingRequestValidator;

    public void bookTrip(BookingRequest bookingRequest, Long userId) {
        bookingRequestValidator.validateBookingRequest(bookingRequest);
        System.out.println(bookingRequest);

        Trip tripToBook = tripRepository.findById(bookingRequest.getTripId())
                .orElseThrow(() -> new TripNotFoundException("Trip with given id doesn't exists"));
        User bookingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        Flight getChosenFlightForTrip = getFlight(bookingRequest.getFlightRequest(), tripToBook, userId);
        flightRepository.save(getChosenFlightForTrip);

        Hotel getChosenHotelForTrip = getHotel(bookingRequest.getBookingHotelRequest(), bookingRequest.getBookingHotelRequest().getHotelId());
        hotelRepository.save(getChosenHotelForTrip);

        BookedTrip bookedTrip = new BookedTrip();
        bookedTrip.setTrip(tripToBook);
        bookedTrip.setFlight(getChosenFlightForTrip);
        bookedTrip.setUser(bookingUser);
        bookedTrip.setHotel(getChosenHotelForTrip);
        bookingUser.getBookedTrips().add(bookedTrip);

        bookedTripRepository.save(bookedTrip);
        userRepository.save(bookingUser);
    }

    private Hotel getHotel(BookingHotelRequest bookingHotelRequest, Long hotelId) {
        HotelInfo hotelInfo = bookingHotelService.getHotelsByPlaceName(bookingHotelRequest);
        if (hotelInfo.getResult() != null) {
            return hotelMapper.mapToHotel(hotelInfo.getResult()
                    .stream()
                    .filter(hotelModel1 -> hotelModel1.getHotelId().equals(hotelId))
                    .findFirst()
                    .orElseThrow(() -> new HotelNotFoundException("Invalid hotel request")));
        } else {
            throw new FlightNotFoundException("Invalid hotel request");
        }
    }


    private Flight getFlight(FlightRequest flightRequest, Trip tripToBook, Long userId) {
        AmadeusFlight amadeusFlight = amadeusFlightSearch.getTripAvailableFlights(tripToBook.getId(), userId, flightRequest);
        if (amadeusFlight.getAvailableFlights() != null) {
            return flightMapper.mapFlightInfo(amadeusFlight.getAvailableFlights()
                    .stream()
                    .filter(flight -> flight.getId().equals(flightRequest.getFlightId()))
                    .findFirst()
                    .orElseThrow(() -> new FlightNotFoundException("Flight with given id doesn't exists")));
        } else {
            throw new FlightNotFoundException("Invalid flight request");
        }
    }

    public List<BookedTrip> getAllBookedTrips(Long userId) {
        User userToGetBookedTrips = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userToGetBookedTrips.getBookedTrips();
    }

    public void deleteBookedTrip(Long bookedTripId) {
        if (bookedTripRepository.existsById(bookedTripId)) {
            bookedTripRepository.deleteById(bookedTripId);
        }
    }

    public void updateBookedTrip(BookingRequest bookingRequest, Long tripId, Long userId) {
        BookedTrip bookedTrip = bookedTripRepository.findById(tripId).orElseThrow(() -> new TripNotFoundException("Booked trip with given id doesn't exists"));
        Flight flight = getFlight(bookingRequest.getFlightRequest(), bookedTrip.getTrip(), userId);
        flightRepository.save(flight);
        bookedTrip.setFlight(flight);
        Hotel hotel = getHotel(bookingRequest.getBookingHotelRequest(), bookingRequest.getBookingHotelRequest().getHotelId());
        hotelRepository.save(hotel);
        bookedTrip.setHotel(hotel);
        bookedTripRepository.save(bookedTrip);

    }
}
