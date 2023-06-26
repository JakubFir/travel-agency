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
public class Segments {
    @JsonProperty("departure")
    private Departure departure;
    @JsonProperty("arrival")
    private Arrival arrival;


}
