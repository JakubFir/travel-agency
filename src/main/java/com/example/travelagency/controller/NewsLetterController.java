package com.example.travelagency.controller;

import com.example.travelagency.mapper.NewsLetterMapper;
import com.example.travelagency.model.dto.NewsLetterDto;
import com.example.travelagency.model.persistence.Newsletter;

import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.service.NewsLetterService;
import com.example.travelagency.service.observer.NewsLetterObservable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> createNewsLetter(@RequestBody Newsletter newsLetter) {
        newsLetterService.createNewsLetter(newsLetter);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<NewsLetterDto>> getAllNewsLetters() {
        return ResponseEntity.ok(newsLetterMapper.mapToNewsletterDtoList(newsLetterService.getAllNewsLetters()));
    }

    @PostMapping(path = "/{newsletterId}")
    public ResponseEntity<Void> subscribeToGivenNewsLetter(@RequestBody Subscriber subscriber, @PathVariable Long newsletterId) {
        observable.register(subscriber, newsletterId);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping(path = "/{newsletterId}")
    public void unsubscribeToGivenNewsLetter(@RequestBody Subscriber subscriber, @PathVariable Long newsletterId) {
        observable.removeObserver(subscriber, newsletterId);
    }
}
