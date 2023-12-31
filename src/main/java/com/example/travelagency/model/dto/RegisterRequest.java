package com.example.travelagency.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public final class RegisterRequest {
    @NotBlank(message = "username cannot be null")
    private final String username;
    @NotBlank
    private final String password;
    @NotBlank
    private final String name;
    @NotBlank
    @Email
    private final String email;
    private final String origin;
    private final String originIataCode;


    public static class RegisterRequestBuilder {
        private String username;
        private String password;
        private String name;
        private String email;
        private String origin;
        private String originIataCode;

        public RegisterRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public RegisterRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public RegisterRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RegisterRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public RegisterRequestBuilder origin(String origin) {
            this.origin = origin;
            return this;
        }

        public RegisterRequestBuilder originIataCode(String originIataCode) {
            this.originIataCode = originIataCode;
            return this;
        }

        public RegisterRequest build() {
            return new RegisterRequest(username, password, name, email, origin, originIataCode);
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

    public String getOrigin() {
        return origin;
    }

    public String getOriginIataCode() {
        return originIataCode;
    }
}
