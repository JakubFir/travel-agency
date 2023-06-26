package com.example.travelagency.controller;

import com.example.travelagency.mapper.NewsLetterMapper;
import com.example.travelagency.model.dto.NewsLetterDto;
import com.example.travelagency.model.persistence.NewsLetter;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.service.NewsLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("newsLetter")
@RequiredArgsConstructor
public class NewsLetterController {
    private final NewsLetterService newsLetterService;
    private final NewsLetterMapper newsLetterMapper;

    @PostMapping()
    public void createNewsLetter(@RequestBody NewsLetter newsLetter) {
        newsLetterService.createNewsLetter(newsLetter);
    }

    @GetMapping()
    public List<NewsLetterDto> getAllNewsLetters() {
        return newsLetterMapper.mapToNewsletterDtoList(newsLetterService.getAllNewsLetters());
    }

    @PostMapping(path = "/{newsletterId}")
    public void subscribeToGivenNewsLetter(@RequestBody Subscriber subscriber, @PathVariable Long newsletterId) {
        newsLetterService.subscribeToGivenNewsLetter(newsletterId, subscriber);
    }
    @DeleteMapping(path = "/{newsletterId}")
    public void unsubscribeToGivenNewsLetter(@RequestBody Subscriber subscriber, @PathVariable Long newsletterId) {
        newsLetterService.unsubscribeToGivenNewsLetter(subscriber, newsletterId);
    }

}
