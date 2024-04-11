package com.dragan.springtraining.config.security.controllers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record AuthenticationRequest(
        @NotNull(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @NotNull(message = "Password is required")
        @Length(min = 6, message = "Password must be at least 6 characters")
        String password
) {
}
