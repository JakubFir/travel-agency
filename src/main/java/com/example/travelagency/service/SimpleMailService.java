package com.example.travelagency.service;

import com.example.travelagency.domain.Mail;
import com.example.travelagency.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleMailService {
    public static final String WEEKLY_MAIL = "weekly mail";
    public static final String NEWS_LETTER = "news letter";
    private final MailCreatorService mailCreatorService;
    private final JavaMailSender javaMailSender;

    public void sendEmail(final Mail mail, String mailType) {
        if (mailType.equals(WEEKLY_MAIL)) {
            javaMailSender.send(createMailMessage(mail));
        } else
            javaMailSender.send(createMailMessage(mail));
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }


}
