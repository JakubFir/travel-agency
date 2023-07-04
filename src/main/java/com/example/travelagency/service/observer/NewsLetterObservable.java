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
    public void register(Observer observer, Long newsLetterId) {
        newsLetterService.subscribeToGivenNewsLetter(newsLetterId, (Subscriber) observer);
    }

    @Override
    public void notifyObs(TripDto tripDto, List<? extends Observer> observerList) {
        for (Observer observer : observerList) {
                subscriberObserver.update(observer, tripDto);
        }
    }

    @Override
    public void removeObserver(Observer observer, Long newsLetterId) {
        newsLetterService.unsubscribeToGivenNewsLetter((Subscriber) observer, newsLetterId);
    }
}
