package com.pfe.backend.controller;

import com.pfe.backend.exception.EmployeeNotFoundException;
import com.pfe.backend.exception.EmployeeProcessingException;
import com.pfe.backend.model.Employee;
import com.pfe.backend.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Cette méthode permet de créer un employé")
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee createdEmployee = employeeService.addEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        } catch (Exception e) {
            log.error("Error while adding the employee: ", e);
            throw new EmployeeProcessingException("Une erreur s'est produite lors de l'enregistrement de l'employé.");
        }
    }

    @Operation(summary = "Cette méthode permet de supprimer un employé par son id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted with id: " + id);
        } catch (Exception e) {
            log.error("Error while deleting the employee: ", e);
            throw new EmployeeNotFoundException("L'employé avec l'ID " + id + " n'a pas été trouvé.");
        }
    }

    @Operation(summary = "Cette méthode permet de récupérer la totalité des employés")
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.error("Error while fetching all employees: ", e);
            throw new EmployeeProcessingException("Une erreur s'est produite lors de la récupération des employés.");
        }
    }

    @Operation(summary = "Cette méthode permet de récupérer les employés appartenant à une entreprise par son id")
    @GetMapping("/entreprise/{entrepriseId}")
    public List<Employee> getEmployesByEntreprise(@PathVariable("entrepriseId") Long entrepriseId) {
        return employeeService.getEmployesByEntreprise(entrepriseId);
    }

    // Exception handling for EmployeeNotFoundException
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Exception handling for EmployeeProcessingException
    @ExceptionHandler(EmployeeProcessingException.class)
    public ResponseEntity<String> handleEmployeeProcessingException(EmployeeProcessingException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
/*package com.pfe.backend.controller;

import com.pfe.backend.model.Employee;
import com.pfe.backend.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Operation (summary="Cette méthode permet de créer un  employé")

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee createdEmployee = employeeService.addEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        } catch (Exception e) {
            log.error("Error while adding the employee: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation (summary="Cette méthode permet de supprimer un employé par son id  ")

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted with id: " + id);
        } catch (Exception e) {
            log.error("Error while deleting the employee: ", e);
            return ResponseEntity.notFound().build();
        }
    }
@Operation (summary="Cette méthode permet de récupérer la totalité des employés")
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.error("Error while fetching all employees: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation (summary="Cette méthode permet de récupérer les employés appartenant à une entreprise par son id ")

    @GetMapping("/entreprise/{entrepriseId}")
    public List<Employee> getEmployesByEntreprise(@PathVariable("entrepriseId") Long entrepriseId) {
        return employeeService.getEmployesByEntreprise(entrepriseId);
    }
} */
