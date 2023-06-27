package com.example.travelagency.service.observer;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Subscriber;

import java.util.List;

public interface Observable {
    void register(Observer observer, Long NewsLetterId);
    void notifyObs(TripDto tripDto, List<Subscriber> subscriberList);
}
