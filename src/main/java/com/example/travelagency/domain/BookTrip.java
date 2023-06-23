package com.example.travelagency.domain;

import com.example.travelagency.model.FlightInfo;
import com.example.travelagency.model.dto.FlightInfoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookTrip {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

}
