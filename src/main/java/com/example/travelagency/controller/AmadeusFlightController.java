package com.example.travelagency.controller;

import com.example.travelagency.model.dto.FlightRequest;
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

    @PostMapping("/{tripId}/{userId}")
    public ResponseEntity<AmadeusFlight> getAvailableFlights(@PathVariable Long tripId, @PathVariable Long userId,@RequestBody FlightRequest flightRequest) {
        return ResponseEntity.ok(amadeusFlightSearch.getAvailableFlights(tripId, userId,flightRequest));
    }
}
