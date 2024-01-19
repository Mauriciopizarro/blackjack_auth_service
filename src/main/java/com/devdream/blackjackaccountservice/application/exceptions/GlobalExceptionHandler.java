package com.devdream.blackjackaccountservice.application.exceptions;

import com.devdream.blackjackaccountservice.infrastructure.security.RestError;
import com.devdream.blackjackaccountservice.application.services.exceptions.EmailUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<RestError> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus httpStatus = (HttpStatus) ex.getStatusCode();
        String errorMessage = extractMessage(ex.getReason());

        RestError restError = new RestError(String.valueOf(httpStatus.value()), errorMessage);
        return new ResponseEntity<>(restError, httpStatus);
    }

    @ExceptionHandler(EmailUsedException.class)
    public ResponseEntity<RestError> handleEmailUsedException(EmailUsedException ex) {
        String errorCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
        String errorMessage = "Email in use";
        RestError restError = new RestError(errorCode, errorMessage);
        return new ResponseEntity<>(restError, HttpStatus.BAD_REQUEST);
    }

    private static String extractMessage(String inputString) {
        String regex = ".*\"message\":\"(.*?)\".*";
        return inputString.replaceAll(regex, "$1");
    }

}
