package com.example.travelagency.service.observer;

import com.example.travelagency.domain.Subscriber;
import com.example.travelagency.repository.SubscriberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public void subscribe(Subscriber subscriber){
        subscriberRepository.save(subscriber);
    }

    public List<Subscriber> getAll() {
      return subscriberRepository.findAll();
    }
}
