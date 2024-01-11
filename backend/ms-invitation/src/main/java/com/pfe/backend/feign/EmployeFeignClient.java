package com.pfe.backend.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.pfe.backend.feign.Employee;

@FeignClient(name = "ms-employe" ,url = "http://localhost:8086/employees")
public interface EmployeFeignClient {

    @PostMapping("/employes")
    ResponseEntity<String> addEmployee(@RequestBody Employee employe);
    
}
