package com.springboot.features.advice;

import com.springboot.features.exception.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .error(e.getLocalizedMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiError> handleException(ResourceNotFound e) {
        ApiError apiError = ApiError
                .builder()
                .timestamp(LocalDateTime.now())
                .error(e.getLocalizedMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


}
