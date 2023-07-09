package com.example.travelagency.model.persistence;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.service.observer.Observer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @Email
    private String email;
    @ManyToMany
    @JoinColumn(name = "news_letter_id")
    private List<Newsletter> newsLetter;
    @Override
    public void update(Observer observer, TripDto tripDto) {
    }
}