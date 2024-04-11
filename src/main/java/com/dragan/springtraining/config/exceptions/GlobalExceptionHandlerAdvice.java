package com.dragan.springtraining.config.exceptions;

import com.dragan.springtraining.teams.exceptions.TeamNotFoundException;
import com.dragan.springtraining.user.exceptions.UserExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(TeamNotFoundException.class)
    public ProblemDetail handleTeamNotFoundException(TeamNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UserExistsException.class)
    public ProblemDetail handleUniqueExceptions(RuntimeException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, f -> f.getDefaultMessage()));
        var detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation errors");

        detail.setProperty("errors", errors);
        return detail;
    }
}