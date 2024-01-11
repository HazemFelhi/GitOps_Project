/*package com.pfe.backend.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pfe.backend.model.Employee;

import java.util.List;

@FeignClient(name = "ms-entreprise",url = "http://localhost:8081/entreprises")
public interface EntrepriseFeignClient {

    @GetMapping("/{id}/employes")
   public List<Employee> getEmployeByIdEntreprise(@PathVariable("id") Long id);
}
*/