package com.example.plans.sharedhub.exceptions;

import com.example.plans.sharedhub.models.response.StringResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException ex) {
        return new ResponseEntity<>(ex.res(), HttpStatusCode.valueOf(200));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<?> handleJsonProcessingException(AppException ex) {
        return new ResponseEntity<>(new StringResponse(ex.getMessage()), HttpStatusCode.valueOf(200));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return new ResponseEntity<>(new StringResponse(ex.getMessage()), HttpStatusCode.valueOf(200));
    }
}
