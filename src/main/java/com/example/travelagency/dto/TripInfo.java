package com.example.travelagency.dto;


import com.example.travelagency.amadeusFlightSearch.dto.FlightInfo;

import com.example.travelagency.domain.Trip;
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

}
