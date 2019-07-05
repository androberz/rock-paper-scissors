package org.aber.rps.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleWrongInputErrors() {
        return getWrongInputMessage();
    }

    @ExceptionHandler(Exception.class)
    public String handleErrors() {
        return getErrorMessage();
    }

    public static String getErrorMessage() {
        return "Something went wrong, try again...";
    }

    private static String getWrongInputMessage() {
        return "Wrong input, try again...";
    }
}
