package com.example.travelagency.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

}