package com.springboot.features.dto;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
    private Long id;

    private String name;


    private String email;

    private int age;

    private String role;

    private LocalDate dateJoining;

    private Boolean isActive;

}

