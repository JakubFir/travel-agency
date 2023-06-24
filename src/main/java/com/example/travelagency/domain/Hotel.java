package com.example.travelagency.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String city_in_trans;
    private double review_score;
    private String address_trans;
    private String min_total_price;
    private String has_free_parking;

    public Hotel(String zip, String city_in_trans, double review_score, String address_trans, String min_total_price, String has_free_parking) {
        this.zip = zip;
        this.city_in_trans = city_in_trans;
        this.review_score = review_score;
        this.address_trans = address_trans;
        this.min_total_price = min_total_price;
        this.has_free_parking = has_free_parking;
    }
}
