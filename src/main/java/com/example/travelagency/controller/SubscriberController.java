package com.example.travelagency.controller;

import com.example.travelagency.domain.Subscriber;
import com.example.travelagency.service.observer.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("news")
@RequiredArgsConstructor
public class SubscriberController {
    private final SubscriberService subscriberService;

    @PostMapping
    public void subscribe(@RequestBody Subscriber subscriber) {
        subscriberService.subscribe(subscriber);
    }

    @GetMapping
    public List<Subscriber> getSubscribers(){
      return subscriberService.getAll();
    }
}
