package com.example.travelagency.service;

import com.example.travelagency.exceptions.NewsLetterNotFoundException;
import com.example.travelagency.exceptions.SubscriberNotFoundException;
import com.example.travelagency.model.persistence.NewsLetter;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.repository.NewsLetterRepository;
import com.example.travelagency.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsLetterService {
    private final NewsLetterRepository newsLetterRepository;
    private final SubscriberRepository subscriberRepository;

    public void subscribeToGivenNewsLetter(Long newsLetterId, Subscriber subscriber) {
        NewsLetter newsLetterToSubscribe = newsLetterRepository.findById(newsLetterId)
                .orElseThrow(() -> new NewsLetterNotFoundException("Newsletter with given id doesn't exists"));
        if (checkIfSubscriberIsRegistered(subscriber)) {
            Subscriber existingObserver = subscriberRepository.findByEmail(subscriber.getEmail())
                    .orElseThrow(() -> new SubscriberNotFoundException("Subscriber with given email doesn't exists"));
            existingObserver.getNewsLetter().add(newsLetterToSubscribe);
            newsLetterToSubscribe.getObserverList().add(existingObserver);
            subscriberRepository.save(existingObserver);
            newsLetterRepository.save(newsLetterToSubscribe);
        } else registerSubscriberAndSubscribe(subscriber, newsLetterToSubscribe);
    }

    private void registerSubscriberAndSubscribe(Subscriber subscriber, NewsLetter newsLetterToSubscribe) {
        Subscriber newObserver = new Subscriber();
        newObserver.setNewsLetter(new ArrayList<>());
        newObserver.setEmail(subscriber.getEmail());
        newObserver.getNewsLetter().add(newsLetterToSubscribe);
        newsLetterToSubscribe.getObserverList().add(newObserver);
        subscriberRepository.save(newObserver);
        newsLetterRepository.save(newsLetterToSubscribe);
    }
    public void unsubscribeToGivenNewsLetter(Subscriber subscriber, Long newsletterId) {
        Subscriber observerToRemoveFromNewsLetter =  subscriberRepository.findByEmail(subscriber.getEmail())
                .orElseThrow(() -> new SubscriberNotFoundException("Subscriber with given email doesnt exists"));
        NewsLetter newsLetter = newsLetterRepository.findById(newsletterId)
                .orElseThrow(() -> new NewsLetterNotFoundException("newsletter with given id doesnt exists"));

        observerToRemoveFromNewsLetter.getNewsLetter().removeIf(news -> news.getId().equals(newsletterId));
        newsLetter.getObserverList().removeIf(sub -> sub.getEmail().equals(observerToRemoveFromNewsLetter.getEmail()));

        subscriberRepository.save(observerToRemoveFromNewsLetter);
        newsLetterRepository.save(newsLetter);
    }

    private boolean checkIfSubscriberIsRegistered(Subscriber subscriber) {
        return subscriberRepository.existsByEmail(subscriber.getEmail());
    }

    public void createNewsLetter(NewsLetter newsLetter) {
        newsLetterRepository.save(newsLetter);
    }

    public List<NewsLetter> getAllNewsLetters() {
        return newsLetterRepository.findAll();
    }

}
