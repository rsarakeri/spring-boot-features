package com.springboot.features.config;

import com.springboot.features.auth.AuditorAwareImpl;
import jdk.jfr.ContentType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
@Configuration
public class AppConfig {

    @Value("${employeeService.base.url}")
    private String BASE_URL;

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditorAwareImpl() {
        return new AuditorAwareImpl();
    }

    @Bean
    @Qualifier("employeeRestClient")
    RestClient getEmployeeRestClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, ((request, response) -> {
                    throw new RuntimeException("Server error occurred");
                }))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
