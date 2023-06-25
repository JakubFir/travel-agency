package com.example.travelagency.service.observer;

import com.example.travelagency.domain.Trip;

public interface Observable {
    void register(Observer observer);
    void notifyObs(Trip trip);
}
