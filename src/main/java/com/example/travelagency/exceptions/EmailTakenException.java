package com.example.travelagency.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmailTakenException extends RuntimeException {
    public EmailTakenException(String msg) {
        super(msg);
    }
}
