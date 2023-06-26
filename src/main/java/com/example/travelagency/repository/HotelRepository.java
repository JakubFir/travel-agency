package com.example.travelagency.repository;

import com.example.travelagency.model.persistence.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
