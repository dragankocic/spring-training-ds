package com.dragan.springtraining.teams.exceptions;

public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException(Integer id) {
        super("Team with id " + id + " not found");
    }
}
