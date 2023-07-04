package com.example.travelagency.service.observer;

import com.example.travelagency.model.dto.TripDto;
import com.example.travelagency.model.persistence.Mail;
import com.example.travelagency.model.persistence.Subscriber;
import com.example.travelagency.service.MailCreatorService;
import com.example.travelagency.service.SimpleMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriberObserver implements Observer {
    private final SimpleMailService simpleMailService;
    private final MailCreatorService mailCreatorService;

    @Override
    public void update(Observer observer, TripDto tripDto) {
            Subscriber subscriber = (Subscriber) observer;
            String email = subscriber.getEmail();
            Mail mail = mailCreatorService.createNewTrioMail(email, tripDto);
            System.out.println(email);
            simpleMailService.sendEmail(mail, mail.getSubject());

    }
}
