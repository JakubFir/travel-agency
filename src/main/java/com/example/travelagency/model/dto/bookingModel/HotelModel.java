package com.example.travelagency.model.dto.bookingModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class HotelModel {
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("city_in_trans")
    private String city_in_trans;
    @JsonProperty("hotel_id")
    private Long hotel_id;
    @JsonProperty("review_score")
    private double review_score;
    @JsonProperty("address_trans")
    private String address_trans;
    @JsonProperty("min_total_price")
    private String min_total_price;
    @JsonProperty("has_free_parking")
    private String has_free_parking;
}
