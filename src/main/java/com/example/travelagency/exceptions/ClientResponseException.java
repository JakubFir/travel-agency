package com.example.travelagency.exceptions;

public class ClientResponseException extends RuntimeException {
    public ClientResponseException(String msg) {
        super(msg);
    }
}
