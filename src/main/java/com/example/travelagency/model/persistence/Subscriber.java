package com.example.travelagency.model.persistence;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.service.observer.Observer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "subscribers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber implements Observer {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    @ManyToMany
    @JoinColumn(name = "news_letter_id")
    private List<NewsLetter> newsLetter;
    @Override
    public void update(Observer observer, TripDto tripDto) {
    }
}