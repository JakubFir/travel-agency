package com.example.travelagency.mapper;

import com.example.travelagency.model.dto.SubscriberDto;

import com.example.travelagency.model.persistence.Subscriber;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriberMapper {

    public SubscriberDto mapToSubscriberDto(Subscriber subscriber) {
        return SubscriberDto.builder()
                .email(subscriber.getEmail())
                .build();
    }

    public List<SubscriberDto> mapToSubscriberDtoList(List<Subscriber> allObservers) {
        return allObservers.stream().map(this::mapToSubscriberDto).collect(Collectors.toList());
    }
}
