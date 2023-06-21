package com.example.travelagency.dto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private String username;
    private String name;
    private String email;
}
