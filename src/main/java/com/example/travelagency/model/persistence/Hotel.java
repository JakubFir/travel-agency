package com.example.travelagency.model.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    private String zip;
    private String cityInTrans;
    private double reviewScore;
    private String addressTrans;
    private String minTotalPrice;
    private boolean hasFreeParking;

    public Hotel(String zip, String cityInTrans, double reviewScore, String addressTrans, String minTotalPrice, boolean hasFreeParking) {
        this.zip = zip;
        this.cityInTrans = cityInTrans;
        this.reviewScore = reviewScore;
        this.addressTrans = addressTrans;
        this.minTotalPrice = minTotalPrice;
        this.hasFreeParking = hasFreeParking;
    }
}
