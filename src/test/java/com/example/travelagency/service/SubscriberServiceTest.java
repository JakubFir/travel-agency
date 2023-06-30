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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriberServiceTest {
    @Mock
    private SubscriberRepository subscriberRepository;
    @Mock
    private NewsLetterRepository newsLetterRepository;

    private SubscriberService subscriberService;
    @BeforeEach
    void setUp() {
        subscriberService = new SubscriberService(subscriberRepository);
    }

    @Test
    void getAllSubscribers() {
    }


}