package com.springboot.features.advice;

import lombok.*;

import java.time.LocalDateTime;


@Data
public class ApiResponse<T> {

    //    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timeStamp;

    private ApiError apiError;

    private T  data;

    public ApiResponse(){
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError){
        this();
        this.apiError = apiError;
    }
}