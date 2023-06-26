package com.example.travelagency.service.observer;

import com.example.travelagency.model.persistence.Trip;

public interface Observable {
    void register(Observer observer);
    void notifyObs(Trip trip);
}
