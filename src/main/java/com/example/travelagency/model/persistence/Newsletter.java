package com.example.travelagency.model.persistence;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "newsLetter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Newsletter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String newsletterTitle;
    @ManyToMany
    private List<Subscriber> observerList;

}
