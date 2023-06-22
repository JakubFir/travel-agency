package com.example.travelagency.controller;

import com.example.travelagency.domain.Trip;
import com.example.travelagency.dto.TripDto;
import com.example.travelagency.dto.TripInfo;
import com.example.travelagency.mapper.TripMapper;
import com.example.travelagency.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;
    private final TripMapper tripMapper;


    @PostMapping
    public void addTrip(@RequestBody Trip trip) {
        tripService.addTrip(trip);
    }

    @GetMapping
    public List<TripDto> getListOfTrips() {
        return tripMapper.mapToTripDtoList(tripService.getListOfTrips());
    }

    @GetMapping(path = "{tripId}")
    public TripInfo getTripInfo(@PathVariable Long tripId) {
        return tripService.getTripInfo(tripId);
    }
}
