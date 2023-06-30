package com.example.travelagency.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    private Long tripId;
    private Long flightId;
    private BookingHotelRequest bookingHotelRequest;
}
