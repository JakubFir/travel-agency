package com.example.travelagency.model.persistence;


import com.example.travelagency.service.observer.Observer;
import com.example.travelagency.service.observer.Observable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Trip  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origin;
    private String originIataCode;
    private String destinationsIataCode;
    private String destination;
    private String description;
    public Trip(String origin, String originIataCode, String destinationsIataCode, String destination, String description) {
        this.origin = origin;
        this.originIataCode = originIataCode;
        this.destinationsIataCode = destinationsIataCode;
        this.destination = destination;
        this.description = description;
    }
}

