package com.example.travelagency.model.dto;

import com.example.travelagency.model.persistence.Subscriber;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Data
public class NewsLetterDto {
    private String name;
    private List<SubscriberDto> subscriberList;
}
