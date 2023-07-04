package com.example.travelagency.service.observer;

import com.example.travelagency.model.dto.TripDto;

public interface Observer {
    void update(Observer observer, TripDto tripDto);
}
