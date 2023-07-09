package com.example.travelagency.exceptions;

public class BadEmailRequest extends RuntimeException {

    public BadEmailRequest(String msg){
        super(msg);
    }
}
