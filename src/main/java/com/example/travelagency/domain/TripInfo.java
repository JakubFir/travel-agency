package com.example.travelagency.domain;


import com.example.travelagency.model.bookingModel.BookingAvailableHotelsInCity;
import com.example.travelagency.model.amadeusModel.FlightInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripInfo {
    private Trip trip;
    private List<FlightInfo> listOfAvailableFlights;
    private List<BookingAvailableHotelsInCity> availableHotelsInCities;
}
