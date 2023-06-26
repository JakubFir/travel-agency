package com.example.travelagency.repository;

import com.example.travelagency.model.persistence.BookedTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTripRepository extends JpaRepository<BookedTrip,Long> {
}
