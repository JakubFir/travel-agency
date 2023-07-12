package com.example.travelagency.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    @NotBlank
    @NonNull
    private Long tripId;
    @NotBlank
    @NonNull
    private Long flightId;
    @NonNull
    @NotBlank
    private BookingHotelRequest bookingHotelRequest;
}
