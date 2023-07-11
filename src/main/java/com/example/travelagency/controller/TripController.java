package com.example.travelagency.controller;

import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.TripInfo;
import com.example.travelagency.mapper.TripMapper;
import com.example.travelagency.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;
    private final TripMapper tripMapper;


    @PostMapping
    public ResponseEntity<Void> addTrip(@RequestBody Trip trip) {
        tripService.addTrip(trip);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<List<TripDto>> getListOfTrips() {
        return ResponseEntity.ok(tripMapper.mapToTripDtoList(tripService.getListOfTrips()));
    }

    @GetMapping(path = "{tripId}/{userId}")
    public ResponseEntity<TripInfo> getTripInfo(@PathVariable Long tripId, @PathVariable Long userId) {
        return ResponseEntity.ok(tripService.getTripInfo(tripId, userId));
    }

}
