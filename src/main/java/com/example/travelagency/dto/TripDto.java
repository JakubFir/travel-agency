package com.example.travelagency.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TripDto {
    private String destinations;
    private String origin;
    private BigDecimal price;
}
