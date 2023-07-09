package com.example.travelagency.service;

import com.example.travelagency.model.persistence.Newsletter;

import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.repository.NewsLetterRepository;
import com.example.travelagency.repository.SubscriberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsletterServiceTest {
    @Mock
    private NewsLetterRepository newsLetterRepository;
    @Mock
    private SubscriberRepository subscriberRepository;

    private NewsLetterService newsLetterService;

    @BeforeEach
    void setUp() {
        newsLetterService = new NewsLetterService(newsLetterRepository, subscriberRepository);
    }

    @Test
    void subscribeToGivenNewsLetter() {
        //Given
        List<Subscriber> observerList = new ArrayList<>();
        Newsletter newsLetter = new Newsletter();
        newsLetter.setObserverList(observerList);
        Subscriber subscriber = new Subscriber();
        when(newsLetterRepository.findById(1L)).thenReturn(Optional.of(newsLetter));

        //When
        newsLetterService.subscribeToGivenNewsLetter(1L, subscriber);

        //Then
        ArgumentCaptor<Subscriber> subscriberArgumentCaptor = ArgumentCaptor.forClass(Subscriber.class);
        verify(subscriberRepository).save(subscriberArgumentCaptor.capture());
        ArgumentCaptor<Newsletter> newsLetterArgumentCaptor = ArgumentCaptor.forClass(Newsletter.class);
        verify(newsLetterRepository).save(newsLetterArgumentCaptor.capture());
    }

    @Test
    void createNewsLetter() {
        //Given
        Newsletter newsLetter = new Newsletter();

        //When
        newsLetterService.createNewsLetter(newsLetter);

        //Then
        verify(newsLetterRepository).save(newsLetter);
    }

    @Test
    void getAllNewsLetters() {
        //Given
        List<Newsletter> newsletterList = new ArrayList<>();

        //When
        List<Newsletter> result = newsLetterService.getAllNewsLetters();

        //Then
        assertThat(result).isEqualTo(newsletterList);
    }
    @Test
    void unsubscribeToGivenNewsLetter() {
        //Given
        Subscriber observer = new Subscriber(1L, "TEST", new ArrayList<>());
        Newsletter newsLetter = new Newsletter(1L, "test", new ArrayList<>());
        List<Subscriber> observerList = new ArrayList<>();
        observerList.add(observer);
        List<Newsletter> newsletterList = new ArrayList<>();
        newsletterList.add(newsLetter);
        observer.setNewsLetter(newsletterList);
        newsLetter.setObserverList(observerList);

        when(subscriberRepository.findByEmail(any())).thenReturn(Optional.of(observer));
        when(newsLetterRepository.findById(1L)).thenReturn(Optional.of(newsLetter));

        //When
        newsLetterService.unsubscribeToGivenNewsLetter(observer, 1L);

        //Then
        ArgumentCaptor<Subscriber> subscriberArgumentCaptor = ArgumentCaptor.forClass(Subscriber.class);
        verify(subscriberRepository).save(subscriberArgumentCaptor.capture());
        ArgumentCaptor<Newsletter> newsLetterArgumentCaptor = ArgumentCaptor.forClass(Newsletter.class);
        verify(newsLetterRepository).save(newsLetterArgumentCaptor.capture());

        assertThat(subscriberArgumentCaptor.getValue().getNewsLetter().size()).isEqualTo(0);
        assertThat(newsLetterArgumentCaptor.getValue().getObserverList().size()).isEqualTo(0);
    }
}