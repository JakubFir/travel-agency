package com.example.travelagency.domain;

import com.example.travelagency.service.SimpleMailService;
import com.example.travelagency.service.observer.Observer;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subscribers")
@Data
public class Subscriber implements Observer {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;


    @Override
    public void update() {
    }
}