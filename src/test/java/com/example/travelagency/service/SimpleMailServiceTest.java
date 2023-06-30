package com.example.travelagency.service;

import com.example.travelagency.model.persistence.Mail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleMailServiceTest {

    @Mock
    private  JavaMailSender javaMailSender;
    private SimpleMailService simpleMailService;

    @BeforeEach
    void setUp() {
        simpleMailService = new SimpleMailService(javaMailSender);
    }

    @Test
    void sendEmail() {
        //Given
        Mail mail = new Mail("test","test","test","test");

        //When
        simpleMailService.sendEmail(mail,SimpleMailService.NEWS_LETTER);

        //Then
        verify(javaMailSender, atLeastOnce()).send(any(SimpleMailMessage.class));
    }
}