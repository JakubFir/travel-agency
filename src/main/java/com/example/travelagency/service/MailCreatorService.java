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
        List<Trip> listOfTrips = tripRepository.findAll();
        Random rnd = new Random();
        return Mail.builder()
                .mailTo(mail)
                .message("test")
                .subject(SimpleMailService.WEEKLY_MAIL)
                .build();
    }


    public Mail createNewTrioMail(String email, TripDto tripDto) {
        return Mail.builder()
                .mailTo(email)
                .message("New trip has been added " + tripDto)
                .subject(SimpleMailService.NEWS_LETTER)
                .build();
    }
}
