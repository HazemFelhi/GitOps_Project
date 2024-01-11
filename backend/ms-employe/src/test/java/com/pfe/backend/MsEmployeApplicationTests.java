package com.pfe.backend;

import com.pfe.backend.model.Employee;
import com.pfe.backend.repository.EmployeeRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MsEmployeApplicationTests {

	@Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setNom("John");
        employee.setPrenom("Doe");
        employee.setEntrepriseId(1L);
        employee.setEmail("john.doe@example.com");
        employee.setNumTel("123456789");
        employee.setServicesCloud(Arrays.asList("Service A", "Service B"));

        Employee createdEmployee = employeeRepository.save(employee);

        Assertions.assertThat(createdEmployee).isNotNull();
        Assertions.assertThat(createdEmployee.getId()).isGreaterThan(0L);
    }

    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Assertions.assertThat(employee).isNotNull();

        System.out.println(employee);
    }

    @Test
    public void testUpdateEmployee() {
        Long employeeId = 1L;
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Assertions.assertThat(employee).isNotNull();

        employee.setNom("Updated Name");
        employeeRepository.save(employee);

        Employee updatedEmployee = employeeRepository.findById(employeeId).orElse(null);
        Assertions.assertThat(updatedEmployee).isNotNull();
        Assertions.assertThat(updatedEmployee.getNom()).isEqualTo("Updated Name");
    }

    @Test
    public void testDeleteEmployee() {
        Long employeeId = 1L;
        employeeRepository.deleteById(employeeId);

        Employee deletedEmployee = employeeRepository.findById(employeeId).orElse(null);
        Assertions.assertThat(deletedEmployee).isNull();
    }

    /*@Test 
    public void testGetEmployeesByEntrepriseId() {
        Long entrepriseId = 1L;
        List<Employee> employees = employeeRepository.findByEntrepriseId(entrepriseId);
        Assertions.assertThat(employees).isNotNull();
        Assertions.assertThat(employees).hasSizeGreaterThan(0);

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    } */
}
