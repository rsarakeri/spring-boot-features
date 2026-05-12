package com.springboot.features.advice;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ApiResponse<T> {

    private T data;

    private LocalDateTime timeStamp;

    private ApiError apiError;

}
