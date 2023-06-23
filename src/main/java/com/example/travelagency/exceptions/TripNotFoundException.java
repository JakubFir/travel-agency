package com.example.travelagency.exceptions;

public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(String msg) {
        super(msg);
    }
}
