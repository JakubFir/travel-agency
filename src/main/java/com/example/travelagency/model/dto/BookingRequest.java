package com.example.travelagency.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    @NotBlank
    private Long tripId;
    @NotBlank
    private FlightRequest flightRequest;
    @NotBlank
    private BookingHotelRequest bookingHotelRequest;
}
