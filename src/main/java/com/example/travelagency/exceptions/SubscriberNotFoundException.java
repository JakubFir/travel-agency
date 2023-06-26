package com.example.travelagency.exceptions;

public class SubscriberNotFoundException extends RuntimeException {

    public SubscriberNotFoundException(String msg) {
        super(msg);
    }
}
