package com.example.travelagency.service;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Mail;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.repository.TripRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailCreatorService {
    private final TripRepository tripRepository;

    public Mail createWeeklyMail(String mail) {
        Trip randomTrip = getRandomTrip();
        if (randomTrip == null) {
            return null;
        }
        return Mail.builder()
                .mailTo(mail)
                .message("A random trip offer " + randomTrip)
                .subject(SimpleMailService.WEEKLY_MAIL)
                .build();
    }

    private Trip getRandomTrip() {
        List<Trip> listOfTrips = tripRepository.findAll();
        if (listOfTrips != null && !listOfTrips.isEmpty()) {
            Random rnd = new Random();
            return listOfTrips.get(rnd.nextInt(listOfTrips.size()));
        }
        return null;
    }
    public Mail createNewsletterMail(String email, TripDto tripDto) {
        return Mail.builder()
                .mailTo(email)
                .message("A new trip has been added " + tripDto)
                .subject(SimpleMailService.NEWS_LETTER)
                .build();
    }
}
