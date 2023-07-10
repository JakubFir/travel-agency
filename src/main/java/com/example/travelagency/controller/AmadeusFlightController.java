package com.example.travelagency.controller;

import com.example.travelagency.mapper.TripMapper;
import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.model.dto.amadeusModel.AmadeusFlight;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class AmadeusFlightController {
    private final AmadeusFlightSearch amadeusFlightSearch;
    private final TripMapper tripMapper;

    @PostMapping("/{userId}")
    public AmadeusFlight getAvailableFlights(@RequestBody TripDto trip, @PathVariable Long userId) {
         return amadeusFlightSearch.getAvailableFlights(tripMapper.mapToTrip(trip), userId);
    }

}
