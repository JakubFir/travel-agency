package com.example.travelagency.model.dto;

import com.example.travelagency.model.persistence.Subscriber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsLetterDto {
    private String name;
    private List<SubscriberDto> subscriberList;
}
