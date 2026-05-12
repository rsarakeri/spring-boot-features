package com.springboot.features.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiError {

    private HttpStatus httpStatus;
    private String message;
    private List<String> subErrors;
}
