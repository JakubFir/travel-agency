package com.example.travelagency.service;

import com.example.travelagency.model.persistence.NewsLetter;

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
class NewsLetterServiceTest {
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
        NewsLetter newsLetter = new NewsLetter();
        newsLetter.setObserverList(observerList);
        Subscriber subscriber = new Subscriber();
        when(newsLetterRepository.findById(1L)).thenReturn(Optional.of(newsLetter));

        //When
        newsLetterService.subscribeToGivenNewsLetter(1L, subscriber);

        //Then
        ArgumentCaptor<Subscriber> subscriberArgumentCaptor = ArgumentCaptor.forClass(Subscriber.class);
        verify(subscriberRepository).save(subscriberArgumentCaptor.capture());
        ArgumentCaptor<NewsLetter> newsLetterArgumentCaptor = ArgumentCaptor.forClass(NewsLetter.class);
        verify(newsLetterRepository).save(newsLetterArgumentCaptor.capture());
    }

    @Test
    void createNewsLetter() {
        //Given
        NewsLetter newsLetter = new NewsLetter();

        //When
        newsLetterService.createNewsLetter(newsLetter);

        //Then
        verify(newsLetterRepository).save(newsLetter);
    }

    @Test
    void getAllNewsLetters() {
        //Given
        List<NewsLetter> newsLetterList = new ArrayList<>();

        //When
        List<NewsLetter> result = newsLetterService.getAllNewsLetters();

        //Then
        assertThat(result).isEqualTo(newsLetterList);
    }
    @Test
    void unsubscribeToGivenNewsLetter() {
        //Given
        Subscriber observer = new Subscriber(1L, "TEST", new ArrayList<>());
        NewsLetter newsLetter = new NewsLetter(1L, "test", new ArrayList<>());
        List<Subscriber> observerList = new ArrayList<>();
        observerList.add(observer);
        List<NewsLetter> newsLetterList = new ArrayList<>();
        newsLetterList.add(newsLetter);
        observer.setNewsLetter(newsLetterList);
        newsLetter.setObserverList(observerList);

        when(subscriberRepository.findByEmail(any())).thenReturn(Optional.of(observer));
        when(newsLetterRepository.findById(1L)).thenReturn(Optional.of(newsLetter));

        //When
        newsLetterService.unsubscribeToGivenNewsLetter(observer, 1L);

        //Then
        ArgumentCaptor<Subscriber> subscriberArgumentCaptor = ArgumentCaptor.forClass(Subscriber.class);
        verify(subscriberRepository).save(subscriberArgumentCaptor.capture());
        ArgumentCaptor<NewsLetter> newsLetterArgumentCaptor = ArgumentCaptor.forClass(NewsLetter.class);
        verify(newsLetterRepository).save(newsLetterArgumentCaptor.capture());

        assertThat(subscriberArgumentCaptor.getValue().getNewsLetter().size()).isEqualTo(0);
        assertThat(newsLetterArgumentCaptor.getValue().getObserverList().size()).isEqualTo(0);
    }
}