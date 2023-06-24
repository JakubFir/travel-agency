package com.example.travelagency.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingHotelRequest {
    private String checkInDate;
    private String checkOutDate;
    private String hotelName;
    private String origin;
    private int adultsNumber;
}
