package com.example.travelagency.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TripDto {
    private String destinations;
    private String origin;
    private String description;
}
