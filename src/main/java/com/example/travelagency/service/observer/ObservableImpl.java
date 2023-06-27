package com.example.travelagency.service.observer;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.repository.SubscriberRepository;
import com.example.travelagency.service.NewsLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObservableImpl implements Observable {
    private final NewsLetterService newsLetterService;
    private final ObserverImpl observer;

    @Override
    public void register(Observer observer, Long newsLetterId) {
        newsLetterService.subscribeToGivenNewsLetter(newsLetterId, (Subscriber) observer);
    }

    @Override
    public void notifyObs(TripDto tripDto, List<Subscriber> subscriberList) {
        for (Subscriber subscriber : subscriberList) {
            observer.update(subscriber, tripDto);
        }
    }
}
