package com.example.travelagency.service.observer;

import com.example.travelagency.model.dto.TripDto;

import java.util.List;

public interface Observable {
    void register(Observer observer, Long newsLetterId);
    void notifyObs(TripDto tripDto, List<? extends Observer> observerList);
    void removeObserver(Observer observer, Long newsLetterId);

}
