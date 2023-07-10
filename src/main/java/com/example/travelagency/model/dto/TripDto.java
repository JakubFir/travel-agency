package com.example.travelagency.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long tripId;
    private String destination;
    private String description;
    private String destinationsIataCode;

    public TripDto(String destination, String description, String destinationsIataCode) {
        this.destination = destination;
        this.description = description;
        this.destinationsIataCode = destinationsIataCode;
    }
}
