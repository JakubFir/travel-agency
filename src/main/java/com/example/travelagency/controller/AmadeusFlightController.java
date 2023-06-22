package com.example.travelagency.controller;

import com.example.travelagency.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.amadeusFlightSearch.dto.AmadeusFlight;
import com.example.travelagency.amadeusFlightSearch.dto.OriginFlight;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class AmadeusFlightController {
    private final AmadeusFlightSearch amadeusFlightSearch;

    @GetMapping
    public AmadeusFlight getAvailableFlights(@RequestBody OriginFlight origin) {
         return amadeusFlightSearch.getFlight(origin);
    }

}
