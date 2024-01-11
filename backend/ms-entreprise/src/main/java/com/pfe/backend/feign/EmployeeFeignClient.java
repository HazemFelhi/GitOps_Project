package com.pfe.backend.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-employe",url = "http://localhost:8086/employees")
public interface EmployeeFeignClient {

    @GetMapping("/entreprise/{entrepriseId}")
    List<Employee> getEmployesByEntreprise(@PathVariable("entrepriseId") Long entrepriseId);


}