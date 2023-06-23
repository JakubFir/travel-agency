package com.example.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightInfo {
    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("itineraries")
    private List<Intineraries> itineraries;
    @JsonProperty("price")
    private FlightPriceInfo price;

}
