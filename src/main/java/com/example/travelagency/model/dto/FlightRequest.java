package com.example.travelagency.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightRequest {
    private String departureDate;
    private Long flightId;

    public FlightRequest(String departureDate) {
        this.departureDate = departureDate;
    }
}
