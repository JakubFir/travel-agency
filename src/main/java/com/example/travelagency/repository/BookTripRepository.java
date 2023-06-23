package com.example.travelagency.repository;

import com.example.travelagency.domain.BookTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTripRepository extends JpaRepository<BookTrip,Long> {
}
