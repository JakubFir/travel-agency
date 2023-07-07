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
    private String cityInTrans;
    @JsonProperty("hotel_id")
    private Long hotelId;
    @JsonProperty("review_score")
    private double reviewScore;
    @JsonProperty("address_trans")
    private String addressTrans;
    @JsonProperty("min_total_price")
    private String minTotalPrice;
    @JsonProperty("has_free_parking")
    private boolean hasFreeParking;
}
