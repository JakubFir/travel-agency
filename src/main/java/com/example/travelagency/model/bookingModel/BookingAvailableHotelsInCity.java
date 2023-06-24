package com.example.travelagency.model.bookingModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingAvailableHotelsInCity {
    @JsonProperty("name")
    private String name;
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("type")
    private String type;
    @JsonProperty("hotels")
    private int hotels;
    @JsonProperty("dest_type")
    private String destType;
    @JsonProperty("region")
    private String region;
    @JsonProperty("latitude")
    private double latitude;
    @JsonProperty("longitude")
    private double longitude;
}
