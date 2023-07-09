package com.example.travelagency.service;

import com.example.travelagency.exceptions.BadEmailRequest;
import com.example.travelagency.exceptions.EmailTakenException;
import com.example.travelagency.exceptions.NewsLetterNotFoundException;
import com.example.travelagency.exceptions.SubscriberNotFoundException;
import com.example.travelagency.model.persistence.Newsletter;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.repository.NewsLetterRepository;
import com.example.travelagency.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsLetterService {
    private final NewsLetterRepository newsLetterRepository;
    private final SubscriberRepository subscriberRepository;

    public void subscribeToGivenNewsLetter(Long newsLetterId, Subscriber subscriber) {
        Newsletter newsletterToSubscribe = newsLetterRepository.findById(newsLetterId)
                .orElseThrow(() -> new NewsLetterNotFoundException("Newsletter with given id doesn't exists"));
        if (subscriberRepository.existsByEmail(subscriber.getEmail())) {
            Subscriber existingObserver = subscriberRepository.findByEmail(subscriber.getEmail())
                    .orElseThrow(() -> new SubscriberNotFoundException("Subscriber with given email doesn't exists"));
            checkIfSubscriberDostSubscribeToNewsletter(existingObserver, newsletterToSubscribe);
            existingObserver.getNewsLetter().add(newsletterToSubscribe);
            newsletterToSubscribe.getObserverList().add(existingObserver);
            subscriberRepository.save(existingObserver);
            newsLetterRepository.save(newsletterToSubscribe);
        } else registerSubscriberAndSubscribe(subscriber, newsletterToSubscribe);
    }

    private void checkIfSubscriberDostSubscribeToNewsletter(Subscriber existingObserver, Newsletter newsletterToSubscribe) {
        if (existingObserver.getNewsLetter().contains(newsletterToSubscribe)) {
            throw new EmailTakenException("User with the given email already subscribes to the given newsletter");
        }
    }

    private void registerSubscriberAndSubscribe(Subscriber subscriber, Newsletter newsletterToSubscribe) {
        Subscriber newObserver = new Subscriber();
        newObserver.setNewsLetter(new ArrayList<>());
        newObserver.setEmail(subscriber.getEmail());
        newObserver.getNewsLetter().add(newsletterToSubscribe);
        newsletterToSubscribe.getObserverList().add(newObserver);
        try {
            subscriberRepository.save(newObserver);
            newsLetterRepository.save(newsletterToSubscribe);
        } catch (TransactionSystemException e) {
            throw new BadEmailRequest("provide a valid Email");
        }
    }

    public void unsubscribeToGivenNewsLetter(Subscriber subscriber, Long newsletterId) {
        Subscriber observerToRemoveFromNewsLetter = subscriberRepository.findByEmail(subscriber.getEmail())
                .orElseThrow(() -> new SubscriberNotFoundException("Subscriber with given email doesnt exists"));
        Newsletter newsLetter = newsLetterRepository.findById(newsletterId)
                .orElseThrow(() -> new NewsLetterNotFoundException("newsletter with given id doesnt exists"));

        observerToRemoveFromNewsLetter.getNewsLetter().removeIf(news -> news.getId().equals(newsletterId));
        newsLetter.getObserverList().removeIf(sub -> sub.getEmail().equals(observerToRemoveFromNewsLetter.getEmail()));

        subscriberRepository.save(observerToRemoveFromNewsLetter);
        newsLetterRepository.save(newsLetter);
    }

    public void createNewsLetter(Newsletter newsLetter) {
        newsLetterRepository.save(newsLetter);
    }

    public List<Newsletter> getAllNewsLetters() {
        return newsLetterRepository.findAll();
    }

}
