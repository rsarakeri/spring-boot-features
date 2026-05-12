package com.springboot.features;

import com.springboot.features.client.EmployeeClient;
import com.springboot.features.client.impl.EmployeeClientImpl;
import com.springboot.features.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FeaturesApplicationTests {

    @Autowired
    private EmployeeClient employeeClient;

    @Test
    void contextLoads() {
    }

    @Test
    @Order(2)
    void checkEmployeeClient() {
        employeeClient.getAllEmployees().stream().forEach(e -> System.out.println(e.toString()));
    }

    @Test
    @Order(1)
    void getEmployeeById() {
        System.out.println(employeeClient.getEmployeeById(2L));
    }

    @Test
    @Order(0)
    void createEmployee() {
        System.out.println(employeeClient.create(new EmployeeDTO(
                null,
				"RAHUL",
                "asd@gmail.com",
                7,
                "ADMIN",
                LocalDate.now(),
                Boolean.TRUE)));
    }

}
