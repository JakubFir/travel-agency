package com.example.travelagency.model.dto;

import com.example.travelagency.model.persistence.Flight;
import com.example.travelagency.model.persistence.Hotel;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookedTripDto {
    private Long userId;
    private String username;
    private Flight flight;
    private Hotel hotel;
}
