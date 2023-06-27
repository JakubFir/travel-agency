package com.example.travelagency.service.observer;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.model.persistence.Trip;

public interface Observer {
    void update(Subscriber subscriber, TripDto tripDto);
}
