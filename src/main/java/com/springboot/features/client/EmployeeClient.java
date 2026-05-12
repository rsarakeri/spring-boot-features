package com.springboot.features.client;

import com.springboot.features.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO create(EmployeeDTO employeeDTO);

    Optional<EmployeeDTO> getEmployeeById(Long id);
}
