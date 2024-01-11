/*package com.pfe.backend.service;


import java.util.Collections;
import java.util.List;
import com.pfe.backend.model.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ApiCall {
    private static final Logger logger = LoggerFactory.getLogger(ApiCall.class);
    @Autowired
    private RestTemplate restTemplate;

    private static final String employeeMicroserviceBaseURL = "http://ms-employe";

    @CircuitBreaker(name = "example", fallbackMethod = "fallback")
    public List<Employee> getEmployeByIdEntreprise(Long entrepriseId) {
        logger.info("Calling employee microservice API using RestTemplate");

        ResponseEntity<List<Employee>> response = restTemplate.exchange(
                employeeMicroserviceBaseURL + "/" + entrepriseId + "/employes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to retrieve employees");
        }
    }

    public List<Employee> fallback(Long entrepriseId, Throwable throwable) {
        logger.error("Fallback method called. Cause: {}", throwable.toString());
        // Return a fallback response or empty list if desired
        return Collections.emptyList();
    }
}
*/