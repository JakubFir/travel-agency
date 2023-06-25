package com.example.travelagency.service.emailScheduler;

import com.example.travelagency.domain.Mail;
import com.example.travelagency.domain.User;
import com.example.travelagency.repository.UserRepository;
import com.example.travelagency.service.MailCreatorService;
import com.example.travelagency.service.SimpleMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class EmailScheduler {
    private final SimpleMailService simpleMailService;
    private final MailCreatorService mailCreatorService;
    private final UserRepository userRepository;

    public void sendWeeklyMailWithRandomTrip() {
        System.out.println("here");
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            Mail mail = mailCreatorService.createWeeklyMail(user.getEmail());
            simpleMailService.sendEmail(mail, SimpleMailService.WEEKLY_MAIL);
        }
    }
}
