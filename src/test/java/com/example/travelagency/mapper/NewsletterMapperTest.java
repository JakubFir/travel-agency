package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.NewsLetterDto;
import com.example.travelagency.model.dto.SubscriberDto;
import com.example.travelagency.model.persistence.Newsletter;
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
class NewsletterMapperTest {
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
        Newsletter newsLetter = new Newsletter();
        newsLetter.setNewsletterTitle("Example Newsletter");
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
        List<Newsletter> newsletters = new ArrayList<>();
        Newsletter newsletter1 = new Newsletter();
        newsletter1.setNewsletterTitle("Newsletter 1");
        List<Subscriber> observerList1 = new ArrayList<>();
        Subscriber observer1 = new Subscriber();
        observer1.setId(1L);
        observer1.setEmail("Joh");
        observerList1.add(observer1);
        newsletter1.setObserverList(observerList1);
        newsletters.add(newsletter1);

        Newsletter newsletter2 = new Newsletter();
        newsletter2.setNewsletterTitle("Newsletter 2");
        List<Subscriber> observerList2 = new ArrayList<>();
        Subscriber observer2 = new Subscriber();
        observer2.setId(2L);
        observer2.setEmail("Jane");
        observerList2.add(observer2);
        newsletter2.setObserverList(observerList2);
        newsletters.add(newsletter2);
        when(subscriberMapper.mapToSubscriberDto(observer1)).thenReturn(new SubscriberDto("Joh"));
        when(subscriberMapper.mapToSubscriberDto(observer2)).thenReturn(new SubscriberDto("Jane"));

        //When
        List<NewsLetterDto> newsLetterDtoList = newsLetterMapper.mapToNewsletterDtoList(newsletters);

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