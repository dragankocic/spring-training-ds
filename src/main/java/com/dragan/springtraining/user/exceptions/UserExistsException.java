package com.dragan.springtraining.user.exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String email) {
        super("User with email " + email + " already exists.");
    }
}
