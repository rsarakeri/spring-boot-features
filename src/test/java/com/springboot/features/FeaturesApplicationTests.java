package com.springboot.features;

import com.springboot.features.client.EmployeeClient;
import com.springboot.features.client.impl.EmployeeClientImpl;
import com.springboot.features.dto.EmployeeDTO;
import com.springboot.features.entity.User;
import com.springboot.features.service.JwtService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
class FeaturesApplicationTests {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmployeeClient employeeClient;

    @Test
    void contextLoads() {
        User user = new User(45l, "as@gmai.com", "1234");

        String token = jwtService.generateToke(user);

        System.out.println(token);

        Long id = jwtService.getUserIdFromToken(token);

        System.out.println(id);
    }

    @Test
    @Order(2)
    void checkEmployeeClient() {
        employeeClient.getAllEmployees().forEach(e -> log.info(e.toString()));
    }

    @Test
    @Order(1)
    void getEmployeeById() {
        log.info(employeeClient.getEmployeeById(2L).toString());
    }

    @Test
    @Order(0)
    void createEmployee() {
        log.error(employeeClient.create(new EmployeeDTO(
                null,
				"RAHUL",
                "asd@gmail.com",
                7,
                "ADMIN",
                LocalDate.now(),
                Boolean.TRUE)).toString());


    }



}
