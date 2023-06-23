package com.example.travelagency.model.dto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private String username;
    private String name;
    private String email;
}
