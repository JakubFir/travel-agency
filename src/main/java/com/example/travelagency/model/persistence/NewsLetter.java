package com.example.travelagency.model.persistence;

import com.example.travelagency.service.observer.Observable;
import com.example.travelagency.service.observer.Observer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "newsLetter")
@Data
public class NewsLetter implements Observable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String newsLetterTitle;
    @ManyToMany
    private List<Subscriber> subscriberList;

    @Override
    public void register(Observer observer) {
        subscriberList.add((Subscriber) observer);
    }

    @Override
    public void notifyObs(Trip trip) {
        for (Subscriber subscriber : subscriberList) {
            System.out.println("notifying " + subscriber.getEmail());
            subscriber.update();
        }
    }
}
