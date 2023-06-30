package com.example.travelagency.controller;

import com.example.travelagency.mapper.SubscriberMapper;
import com.example.travelagency.model.dto.SubscriberDto;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "subscribers")
@RequiredArgsConstructor
public class SubscriberController {

    private final SubscriberService subscriberService;
    private final SubscriberMapper subscriberMapper;

    @GetMapping
    public List<SubscriberDto> getAllSubscribers(){
        return subscriberMapper.mapToSubscriberDtoList(subscriberService.getAllSubscribers());
    }
}
