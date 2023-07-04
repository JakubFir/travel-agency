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
    private String destination;
    private String origin;
    private String description;
    private String originIataCode;
    private String destinationsIataCode;

}
