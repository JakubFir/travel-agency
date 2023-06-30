package com.example.travelagency.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public final class RegisterRequest {
    private final String username;
    private final String password;
    private final String name;
    private final String email;

    public static class RegisterRequestBuilder {
        private String username;
        private String password;
        private String name;
        private String email;
        public RegisterRequestBuilder username(String username){
            this.username = username;
            return this;
        }
        public RegisterRequestBuilder password(String password){
            this.password = password;
            return this;
        }
        public RegisterRequestBuilder name(String name){
            this.name = name;
            return this;
        }
        public RegisterRequestBuilder email(String email){
            this.email = email;
            return this;
        }
        public RegisterRequest build() {
            return new RegisterRequest(username, password, name, email);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
