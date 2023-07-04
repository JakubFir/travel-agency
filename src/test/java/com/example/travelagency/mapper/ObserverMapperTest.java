package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.SubscriberDto;
import com.example.travelagency.model.persistence.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ObserverMapperTest {
    private SubscriberMapper subscriberMapper;

    @BeforeEach
    void setUp() {
        subscriberMapper = new SubscriberMapper();
    }

    @Test
    void mapToSubscriberDto() {
        //Given
        Subscriber subscriber = new Subscriber();
        subscriber.setEmail("example@example.com");

        //When
        SubscriberDto subscriberDto = subscriberMapper.mapToSubscriberDto(subscriber);

        //Then
        assertEquals("example@example.com", subscriberDto.getEmail());
    }

    @Test
    void mapToSubscriberDtoList() {
        //Given
        List<Subscriber> observers = new ArrayList<>();
        Subscriber observer1 = new Subscriber();
        observer1.setEmail("subscriber1@example.com");
        observers.add(observer1);

        Subscriber observer2 = new Subscriber();
        observer2.setEmail("subscriber2@example.com");
        observers.add(observer2);

        //When
        List<SubscriberDto> subscriberDtoList = subscriberMapper.mapToSubscriberDtoList(observers);

        //Then
        assertEquals(2, subscriberDtoList.size());

        SubscriberDto subscriberDto1 = subscriberDtoList.get(0);
        assertEquals("subscriber1@example.com", subscriberDto1.getEmail());

        SubscriberDto subscriberDto2 = subscriberDtoList.get(1);
        assertEquals("subscriber2@example.com", subscriberDto2.getEmail());
    }
}