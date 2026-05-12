package com.springboot.features.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {

    private LocalDateTime timestamp;

    private String error;

    private HttpStatus status;
}
