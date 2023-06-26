package com.example.travelagency.model.persistence;


import com.example.travelagency.model.dto.bookingModel.BookingAvailableHotelsInCity;
import com.example.travelagency.model.dto.amadeusModel.FlightInfo;

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
