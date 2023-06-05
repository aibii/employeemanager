package tech.getarrays.employeemanager.model.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.model.exception.UserNotFoundException;
import tech.getarrays.employeemanager.model.repo.EmployeeRepo;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }
    
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        // return employeeRepo.findEmployeeById(id)
        //        .orElseThrow(() -> new UserNotFoundException ("User by id " + id + " user not found"));

        Optional<Employee> optionalEmployee = employeeRepo.findEmployeeById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
        throw new UserNotFoundException("User by id " + id + " not found");
    }

        // Employee optionalEmployee = findEmployeeById(id);
        // Employee employee = optionalEmployee.orElseThrow(() -> new UserNotFoundException("Employee not found"));

// Rest of the code with the retrieved employee object
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }
}
