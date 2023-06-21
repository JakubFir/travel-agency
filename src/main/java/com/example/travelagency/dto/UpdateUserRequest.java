package com.example.travelagency.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateUserRequest {
    private String username;
    private String password;
    private String name;
    private String email;
}
