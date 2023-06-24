package com.example.travelagency.controller;

import com.example.travelagency.domain.Trip;
import com.example.travelagency.service.amadeusFlightSearch.client.AmadeusFlightSearch;
import com.example.travelagency.model.amadeusModel.AmadeusFlight;
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
    public AmadeusFlight getAvailableFlights(@RequestBody Trip origin) {
         return amadeusFlightSearch.getAvailableFlights(origin);
    }

}
