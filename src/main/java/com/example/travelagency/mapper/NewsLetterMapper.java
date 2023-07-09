package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.NewsLetterDto;
import com.example.travelagency.model.persistence.Newsletter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsLetterMapper {
    private final SubscriberMapper subscriberMapper;

    public NewsLetterDto mapToNewsletterDto(Newsletter newsLetter) {
        return NewsLetterDto.builder()
                .name(newsLetter.getNewsletterTitle())
                .subscriberList(newsLetter.getObserverList().stream().map(subscriberMapper::mapToSubscriberDto).collect(Collectors.toList()))
                .build();
    }

    public List<NewsLetterDto> mapToNewsletterDtoList(List<Newsletter> allNewsletters) {
        return allNewsletters.stream().map(this::mapToNewsletterDto).collect(Collectors.toList());
    }
}
