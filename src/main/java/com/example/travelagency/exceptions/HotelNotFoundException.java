package com.example.travelagency.exceptions;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(String msg) {
        super(msg);
    }
}
