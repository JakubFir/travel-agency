package com.example.travelagency.repository;

import com.example.travelagency.model.persistence.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsLetterRepository extends JpaRepository<Newsletter, Long> {
}
