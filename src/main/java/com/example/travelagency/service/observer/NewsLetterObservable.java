package com.example.travelagency.service.observer;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.service.NewsLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsLetterObservable implements Observable {
    private final NewsLetterService newsLetterService;
    private final SubscriberObserver subscriberObserver;

    @Override
    public void register(Subscriber subscriber, Long newsLetterId) {
        newsLetterService.subscribeToGivenNewsLetter(newsLetterId, subscriber);
    }

    @Override
    public void notifyObs(TripDto tripDto, List<Subscriber> subscriberList) {
        for (Subscriber subscriber : subscriberList) {
            subscriberObserver.update(subscriber, tripDto);
        }
    }

    @Override
    public void removeObserver(Subscriber subscriber, Long newsLetterId) {
        newsLetterService.unsubscribeToGivenNewsLetter(subscriber,newsLetterId);
    }
}
