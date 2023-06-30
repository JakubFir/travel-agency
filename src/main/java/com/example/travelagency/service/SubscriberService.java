package com.example.travelagency.service;

import com.example.travelagency.exceptions.NewsLetterNotFoundException;
import com.example.travelagency.exceptions.SubscriberNotFoundException;
import com.example.travelagency.model.persistence.NewsLetter;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.repository.NewsLetterRepository;
import com.example.travelagency.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;


    public List<Subscriber> getAllSubscribers() {
        return subscriberRepository.findAll();
    }


}
