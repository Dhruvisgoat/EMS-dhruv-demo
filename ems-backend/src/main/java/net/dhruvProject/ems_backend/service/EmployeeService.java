package net.dhruvProject.ems_backend.service;

import net.dhruvProject.ems_backend.dto.EmployeeDto;
import net.dhruvProject.ems_backend.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    // we can't define the function as we are using interfaces , we need
    // to implement the function later on only

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);

    void deleteEmployee(Long employeeId);
}