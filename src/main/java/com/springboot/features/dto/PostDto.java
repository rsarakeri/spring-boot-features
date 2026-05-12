package com.springboot.features.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

}
