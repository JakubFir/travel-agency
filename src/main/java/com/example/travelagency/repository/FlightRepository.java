package com.example.travelagency.repository;

import com.example.travelagency.model.persistence.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long>{
}
