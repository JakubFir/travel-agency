package com.example.travelagency.controller;

import com.example.travelagency.mapper.TripMapper;
import com.example.travelagency.model.dto.FlightRequest;
import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.model.dto.amadeusModel.AmadeusFlight;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class AmadeusFlightController {
    private final AmadeusFlightSearch amadeusFlightSearch;
    private final TripMapper tripMapper;

    @PostMapping("/{tripId}/{userId}")
    public ResponseEntity<AmadeusFlight> getAvailableFlights(@PathVariable Long tripId, @PathVariable Long userId,@RequestBody FlightRequest flightRequest) {
        return ResponseEntity.ok(amadeusFlightSearch.getAvailableFlights(tripId, userId,flightRequest));
    }
}
