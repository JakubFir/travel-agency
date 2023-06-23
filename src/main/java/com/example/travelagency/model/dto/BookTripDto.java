package com.example.travelagency.model.dto;

import com.example.travelagency.domain.Flight;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookTripDto {
    private Long userId;
    private String username;
    private Flight flight;

}
