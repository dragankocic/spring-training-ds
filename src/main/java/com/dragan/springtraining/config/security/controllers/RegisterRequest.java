package com.dragan.springtraining.config.security.controllers;

import com.dragan.springtraining.user.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterRequest(
        @Length(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        @NotBlank(message = "First name is required")
        String firstName,

        @Length(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password is required")
        @Length(min = 6, message = "Password must be at least 6 characters")
        String password,

        Role role
) {
}
