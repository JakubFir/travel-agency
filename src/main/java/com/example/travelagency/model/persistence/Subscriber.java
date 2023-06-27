package com.example.travelagency.model.persistence;

import com.example.travelagency.service.observer.Observer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "subscribers")
@Data
public class Subscriber {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    @ManyToMany
    @JoinColumn(name = "news_letter_id")
    private List<NewsLetter> newsLetter;

}