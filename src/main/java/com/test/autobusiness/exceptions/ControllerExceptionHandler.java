package com.test.autobusiness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String NOT_FOUND_MESSAGE =
            "Element with such id was not found";

    @ExceptionHandler({IndexOutOfBoundsException.class, NoSuchElementException.class})
    //@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = NOT_FOUND_MESSAGE)
    public HashMap<String, String> handleIndexOutOfBoundsException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", NOT_FOUND_MESSAGE);
        response.put("error", e.getClass().getSimpleName());

        return response;
    }
}
