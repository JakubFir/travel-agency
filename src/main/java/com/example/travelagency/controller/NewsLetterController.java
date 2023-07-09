package com.example.travelagency.controller;

import com.example.travelagency.mapper.NewsLetterMapper;
import com.example.travelagency.model.dto.NewsLetterDto;
import com.example.travelagency.model.persistence.Newsletter;

import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.service.NewsLetterService;
import com.example.travelagency.service.observer.NewsLetterObservable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("newsletter")
@RequiredArgsConstructor
public class NewsLetterController {
    private final NewsLetterService newsLetterService;
    private final NewsLetterMapper newsLetterMapper;
    private final NewsLetterObservable observable;

    @PostMapping()
    public void createNewsLetter(@RequestBody Newsletter newsLetter) {
        newsLetterService.createNewsLetter(newsLetter);
    }

    @GetMapping()
    public List<NewsLetterDto> getAllNewsLetters() {
        return newsLetterMapper.mapToNewsletterDtoList(newsLetterService.getAllNewsLetters());
    }

    @PostMapping(path = "/{newsletterId}")
    public void subscribeToGivenNewsLetter(@RequestBody Subscriber subscriber, @PathVariable Long newsletterId) {
        observable.register(subscriber,newsletterId);
    }
    @DeleteMapping(path = "/{newsletterId}")
    public void unsubscribeToGivenNewsLetter(@RequestBody Subscriber subscriber, @PathVariable Long newsletterId) {
        observable.removeObserver(subscriber, newsletterId);
    }
}
