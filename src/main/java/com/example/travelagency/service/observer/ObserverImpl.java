package com.example.travelagency.service.observer;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Mail;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.model.persistence.Trip;
import com.example.travelagency.service.MailCreatorService;
import com.example.travelagency.service.SimpleMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObserverImpl implements Observer {
    private final SimpleMailService simpleMailService;
    private final MailCreatorService mailCreatorService;

    @Override
    public void update(Subscriber subscriber, TripDto tripDto) {
        Mail mail = mailCreatorService.createNewTrioMail(subscriber.getEmail(), tripDto);
        simpleMailService.sendEmail(mail, mail.getSubject());
    }
}
