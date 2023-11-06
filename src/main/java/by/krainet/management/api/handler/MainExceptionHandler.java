package by.krainet.management.api.handler;

import by.krainet.management.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(DirectionNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAboutDirectionNotFound(DirectionNotFoundException exception) {
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                ZonedDateTime.now());
    }

    @ExceptionHandler(DirectionExistException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleAboutDirectionAlreadyExists(DirectionExistException exception) {
        return new ErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage(),
                ZonedDateTime.now());
    }

    @ExceptionHandler(TestNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAboutTestNotFound(TestNotFoundException exception) {
        return new ErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage(),
                ZonedDateTime.now());
    }

    @ExceptionHandler(TestExistException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleAboutTestAlreadyExists(TestExistException exception) {
        return new ErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage(),
                ZonedDateTime.now());
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAboutCandidateNotFound(CandidateNotFoundException exception) {
        return new ErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage(),
                ZonedDateTime.now());
    }

    @ExceptionHandler(CandidateExistException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleAboutCandidateAlreadyExists(CandidateExistException exception) {
        return new ErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage(),
                ZonedDateTime.now());
    }

    @ExceptionHandler(CandidateTestNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleAboutCandidateTestNotFound(CandidateTestNotFoundException exception) {
        return new ErrorResponse(
                HttpStatus.NO_CONTENT.value(),
                exception.getMessage(),
                ZonedDateTime.now());
    }
}
