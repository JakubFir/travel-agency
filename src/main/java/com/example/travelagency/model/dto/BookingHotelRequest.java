package com.example.travelagency.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingHotelRequest {
    private String checkInDate;
    private String checkOutDate;
    private String placeName;
    private String origin;
    private int adultsNumber;
    private Long hotelId;
}
