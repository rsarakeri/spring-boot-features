package com.springboot.features.client.impl;

import com.springboot.features.advice.ApiResponse;
import com.springboot.features.client.EmployeeClient;
import com.springboot.features.dto.EmployeeDTO;
import com.springboot.features.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        ApiResponse<List<EmployeeDTO>> response = restClient.get()
                .uri("/employee")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        log.debug("Successfully retried employee details");
        log.info("Retrieved employees list : {} {} {}", 465, response, 9);
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
                        log.error(new String(response1.getBody().readAllBytes()));
                        new ResourceNotFound("could not create employee");
                    }))
                    .body(new ParameterizedTypeReference<>() {
                    });

            return response.getData();
        } catch (Exception e) {
            log.error("Exception occurred in create employee", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        log.info("Trying to retrieve employee id : {}", id);
        ApiResponse<Optional<EmployeeDTO>> response = restClient.get()
                .uri("/employee/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return response.getData();
    }
}
