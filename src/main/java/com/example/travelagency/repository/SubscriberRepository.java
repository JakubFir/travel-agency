package com.example.travelagency.repository;

import com.example.travelagency.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
}
