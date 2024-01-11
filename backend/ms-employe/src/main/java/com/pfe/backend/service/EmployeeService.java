package com.pfe.backend.service;

import java.util.List;

import com.pfe.backend.model.Employee;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    void deleteEmployee(Long id);

   /*  Employee getEmployeeById(Long id);
          Employee updateEmployee(Long id, Employee employee); */
         List<Employee> getAllEmployees(); 


         List<Employee> getEmployesByEntreprise(Long entrepriseId) ;




    

}
