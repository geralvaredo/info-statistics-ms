package com.stadistic.infostatisticsms.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String MESSAGE_DESCRIPTION = "mensaje";

    @ExceptionHandler(PersonException.class)
    public ResponseEntity<Map<String,Object>> handleInternalException(PersonException exception) {
        Map<String, Object> response = new HashMap<>();

        response.put(MESSAGE_DESCRIPTION, exception.getMessageDescription());

        return new ResponseEntity<>(response, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
