package com.example.travelagency.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String msg) {
        super(msg);
    }
}
