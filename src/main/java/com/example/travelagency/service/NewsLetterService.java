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
            Subscriber existingSubscriber = subscriberRepository.findByEmail(subscriber.getEmail())
                    .orElseThrow(() -> new SubscriberNotFoundException("Subscriber with given email doesn't exists"));
            existingSubscriber.getNewsLetter().add(newsLetterToSubscribe);
            newsLetterToSubscribe.getSubscriberList().add(existingSubscriber);
            subscriberRepository.save(existingSubscriber);
            newsLetterRepository.save(newsLetterToSubscribe);
        } else registerSubscriberAndSubscribe(subscriber, newsLetterToSubscribe);
    }

    private void registerSubscriberAndSubscribe(Subscriber subscriber, NewsLetter newsLetterToSubscribe) {
        Subscriber newSubscriber = new Subscriber();
        newSubscriber.setNewsLetter(new ArrayList<>());
        newSubscriber.setEmail(subscriber.getEmail());
        newSubscriber.getNewsLetter().add(newsLetterToSubscribe);
        newsLetterToSubscribe.getSubscriberList().add(newSubscriber);
        subscriberRepository.save(newSubscriber);
        newsLetterRepository.save(newsLetterToSubscribe);
    }
    public void unsubscribeToGivenNewsLetter(Subscriber subscriber, Long newsletterId) {
        Subscriber subscriberToRemoveFromNewsLetter =  subscriberRepository.findByEmail(subscriber.getEmail())
                .orElseThrow(() -> new SubscriberNotFoundException("Subscriber with given email doesnt exists"));
        NewsLetter newsLetter = newsLetterRepository.findById(newsletterId)
                .orElseThrow(() -> new NewsLetterNotFoundException("newsletter with given id doesnt exists"));

        subscriberToRemoveFromNewsLetter.getNewsLetter().removeIf(news -> news.getId().equals(newsletterId));
        newsLetter.getSubscriberList().removeIf(sub -> sub.getEmail().equals(subscriberToRemoveFromNewsLetter.getEmail()));

        subscriberRepository.save(subscriberToRemoveFromNewsLetter);
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
