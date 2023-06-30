package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.NewsLetterDto;
import com.example.travelagency.model.dto.SubscriberDto;
import com.example.travelagency.model.persistence.NewsLetter;
import com.example.travelagency.model.persistence.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class NewsLetterMapperTest {
    @Mock
    private NewsLetterMapper newsLetterMapper;
    @Mock
    private SubscriberMapper subscriberMapper;

    @BeforeEach
    void setUp() {
        newsLetterMapper = new NewsLetterMapper(subscriberMapper);
    }

    @Test
    void mapToNewsletterDto() {
        // Given
        NewsLetter newsLetter = new NewsLetter();
        newsLetter.setNewsLetterTitle("Example Newsletter");
        List<Subscriber> subscriberList = new ArrayList<>();
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setId(1L);
        subscriber1.setEmail("John");
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setId(2L);
        subscriber2.setEmail("Jane");
        subscriberList.add(subscriber1);
        subscriberList.add(subscriber2);
        newsLetter.setSubscriberList(subscriberList);

        when(subscriberMapper.mapToSubscriberDto(subscriber1)).thenReturn(new SubscriberDto("John"));
        when(subscriberMapper.mapToSubscriberDto(subscriber2)).thenReturn(new SubscriberDto("Jane"));

        // When
        NewsLetterDto newsLetterDto = newsLetterMapper.mapToNewsletterDto(newsLetter);

        // Then
        assertEquals("Example Newsletter", newsLetterDto.getName());
        assertEquals(2, newsLetterDto.getSubscriberList().size());}

    @Test
    void mapToNewsletterDtoList() {
        // Given
        List<NewsLetter> newsLetters = new ArrayList<>();
        NewsLetter newsLetter1 = new NewsLetter();
        newsLetter1.setNewsLetterTitle("Newsletter 1");
        List<Subscriber> subscriberList1 = new ArrayList<>();
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setId(1L);
        subscriber1.setEmail("Joh");
        subscriberList1.add(subscriber1);
        newsLetter1.setSubscriberList(subscriberList1);
        newsLetters.add(newsLetter1);

        NewsLetter newsLetter2 = new NewsLetter();
        newsLetter2.setNewsLetterTitle("Newsletter 2");
        List<Subscriber> subscriberList2 = new ArrayList<>();
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setId(2L);
        subscriber2.setEmail("Jane");
        subscriberList2.add(subscriber2);
        newsLetter2.setSubscriberList(subscriberList2);
        newsLetters.add(newsLetter2);
        when(subscriberMapper.mapToSubscriberDto(subscriber1)).thenReturn(new SubscriberDto("Joh"));
        when(subscriberMapper.mapToSubscriberDto(subscriber2)).thenReturn(new SubscriberDto("Jane"));

        //When
        List<NewsLetterDto> newsLetterDtoList = newsLetterMapper.mapToNewsletterDtoList(newsLetters);

        //Then
        assertEquals(2, newsLetterDtoList.size());

        NewsLetterDto newsLetterDto1 = newsLetterDtoList.get(0);
        assertEquals("Newsletter 1", newsLetterDto1.getName());
        assertEquals(1, newsLetterDto1.getSubscriberList().size());
        NewsLetterDto newsLetterDto2 = newsLetterDtoList.get(1);
        assertEquals("Newsletter 2", newsLetterDto2.getName());
        assertEquals(1, newsLetterDto2.getSubscriberList().size());


    }
}