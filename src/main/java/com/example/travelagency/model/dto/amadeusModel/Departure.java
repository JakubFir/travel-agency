package com.example.travelagency.model.dto.amadeusModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Departure {
    @JsonProperty("iataCode")
    private String iataCode;
    @JsonProperty("at")
    private String date;
}
