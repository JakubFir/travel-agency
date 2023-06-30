package com.example.travelagency.service.observer;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Subscriber;

import java.util.List;

public interface Observable {
    void register(Subscriber subscriber, Long newsLetterId);
    void notifyObs(TripDto tripDto, List<Subscriber> subscriberList);
    void removeObserver(Subscriber subscriber, Long newsLetterId);

}
