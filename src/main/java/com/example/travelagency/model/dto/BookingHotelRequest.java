package com.example.travelagency.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingHotelRequest {
    @NotBlank
    private String checkInDate;
    @NotBlank
    private String checkOutDate;
    @NotBlank
    @NonNull
    private String placeName;
    @NotBlank
    private String destination;
    @NotBlank
    private int adultsNumber;
    @NotBlank
    private Long hotelId;

    public BookingHotelRequest(String checkInDate, String checkOutDate, String placeName, String destination, int adultsNumber) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.placeName = placeName;
        this.destination = destination;
        this.adultsNumber = adultsNumber;
    }
}
