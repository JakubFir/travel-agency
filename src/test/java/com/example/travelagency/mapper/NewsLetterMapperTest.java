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
        List<Subscriber> observerList = new ArrayList<>();
        Subscriber observer1 = new Subscriber();
        observer1.setId(1L);
        observer1.setEmail("John");
        Subscriber observer2 = new Subscriber();
        observer2.setId(2L);
        observer2.setEmail("Jane");
        observerList.add(observer1);
        observerList.add(observer2);
        newsLetter.setObserverList(observerList);

        when(subscriberMapper.mapToSubscriberDto(observer1)).thenReturn(new SubscriberDto("John"));
        when(subscriberMapper.mapToSubscriberDto(observer2)).thenReturn(new SubscriberDto("Jane"));

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
        List<Subscriber> observerList1 = new ArrayList<>();
        Subscriber observer1 = new Subscriber();
        observer1.setId(1L);
        observer1.setEmail("Joh");
        observerList1.add(observer1);
        newsLetter1.setObserverList(observerList1);
        newsLetters.add(newsLetter1);

        NewsLetter newsLetter2 = new NewsLetter();
        newsLetter2.setNewsLetterTitle("Newsletter 2");
        List<Subscriber> observerList2 = new ArrayList<>();
        Subscriber observer2 = new Subscriber();
        observer2.setId(2L);
        observer2.setEmail("Jane");
        observerList2.add(observer2);
        newsLetter2.setObserverList(observerList2);
        newsLetters.add(newsLetter2);
        when(subscriberMapper.mapToSubscriberDto(observer1)).thenReturn(new SubscriberDto("Joh"));
        when(subscriberMapper.mapToSubscriberDto(observer2)).thenReturn(new SubscriberDto("Jane"));

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