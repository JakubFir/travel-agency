package com.example.travelagency.validator;

import com.example.travelagency.model.dto.BookingRequest;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class BookingRequestValidator {
    public boolean validateBookingRequest(BookingRequest bookingRequest) {
        Long flightId = bookingRequest.getFlightRequest().getFlightId();
        Long tripId = bookingRequest.getTripId();
        if (flightId == null || tripId == null) {
            throw new ValidationException("provide a valid booking request");
        }
        return true;
    }
}
