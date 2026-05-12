package com.springboot.features.client.impl;

import com.springboot.features.advice.ApiResponse;
import com.springboot.features.client.EmployeeClient;
import com.springboot.features.dto.EmployeeDTO;
import com.springboot.features.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        ApiResponse<List<EmployeeDTO>> response = restClient.get()
                .uri("/employee")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return response.getData();
    }

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        try {
            ApiResponse<EmployeeDTO> response = restClient.post()
                    .uri("/employee")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response1) -> {
                        System.out.println(new String(response1.getBody().readAllBytes()));
                        new ResourceNotFound("could not create employee");
                    }))
                    .body(new ParameterizedTypeReference<>() {
                    });

            return response.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        ApiResponse<Optional<EmployeeDTO>> response = restClient.get()
                .uri("/employee/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return response.getData();
    }
}
