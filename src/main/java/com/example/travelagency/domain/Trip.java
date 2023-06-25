package com.example.travelagency.domain;


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
public class Trip implements Observable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origin;
    private String originIataCode;
    private String destinationsIataCode;
    private String destination;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trip")
    private List<Subscriber> subscriberList;

    @Override
    public void register(Observer observer) {
        subscriberList.add((Subscriber) observer);
    }
    @Override
    public void notifyObs(Trip trip) {
        for (Observer observer : subscriberList) {
            observer.update();
        }
    }

    public Trip(String origin, String originIataCode, String destinationsIataCode, String destination, String description, List<Subscriber> subscriberList) {
        this.origin = origin;
        this.originIataCode = originIataCode;
        this.destinationsIataCode = destinationsIataCode;
        this.destination = destination;
        this.description = description;
        this.subscriberList = subscriberList;
    }
}

