package com.pfe.backend.service;

import java.util.List;
import java.util.Optional;

import com.pfe.backend.model.Employee;
import com.pfe.backend.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

  /*   @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setNom(employee.getNom());
            existingEmployee.setPrenom(employee.getPrenom());
            existingEmployee.setEntrepriseId(employee.getEntrepriseId());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setNumTel(employee.getNumTel());
            existingEmployee.setServicesCloud(employee.getServicesCloud());
            return employeeRepository.save(existingEmployee);
        } else {
            return null;
        }
    }*/
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        optionalEmployee.ifPresent(employeeRepository::delete);
    }


    @Override
    public List<Employee> getEmployesByEntreprise(Long entrepriseId) {
        return employeeRepository.findByEntrepriseId(entrepriseId);
    }

 
}
