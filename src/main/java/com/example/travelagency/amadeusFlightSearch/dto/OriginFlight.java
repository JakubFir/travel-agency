package com.example.travelagency.amadeusFlightSearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginFlight {
    private String origin;
    private String destination;
}
