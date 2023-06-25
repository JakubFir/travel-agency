package com.example.travelagency.service;

import com.example.travelagency.domain.Mail;
import com.example.travelagency.domain.Trip;
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


}
