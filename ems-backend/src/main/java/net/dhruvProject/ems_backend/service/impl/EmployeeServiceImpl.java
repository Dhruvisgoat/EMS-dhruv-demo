package net.dhruvProject.ems_backend.service.impl;

import lombok.AllArgsConstructor;
import net.dhruvProject.ems_backend.dto.EmployeeDto;
import net.dhruvProject.ems_backend.entity.Employee;
import net.dhruvProject.ems_backend.exception.ResourceNotFoundException;
import net.dhruvProject.ems_backend.mapper.EmployeeMapper;
import net.dhruvProject.ems_backend.repository.EmployeeRepository;
import net.dhruvProject.ems_backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//Service Annotation tells the spring container to create spring bean for this class
//a Spring bean is a managed object managed by the Spring IoC container. It provides a flexible and powerful
// way to manage application components, handle dependencies, and apply cross-cutting concerns, promoting modular,
// loosely coupled, and easily testable code.

@AllArgsConstructor
// public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    } , so basically it will create all arguments constructors for class for all the properties

public class EmployeeServiceImpl implements EmployeeService {
    //By injecting this dependency into the EmployeeServiceImpl class (usually through a constructor or a setter method),
    // the EmployeeServiceImpl class can use the methods provided by the EmployeeRepository class to save and retrieve
    // employee data without needing to know the intricate details of how the database interaction works.
    private EmployeeRepository employeeRepository;

    @Override
    // if I make some typo in the name of createEmployee then override will turn red
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Mapping EmployeeDto to Employee entity
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);

        // Saving the Employee entity using the injected EmployeeRepository

        Employee savedEmployee = employeeRepository.save(employee);
        // Mapping the saved Employee entity back to an EmployeeDto for dto consistency
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee doesn't exist with given id:"+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    // Method to retrieve all employees and convert them to a list of EmployeeDto objects
    public List<EmployeeDto> getAllEmployees() {
        // Fetch all Employee entities from the repository
        // The employeeRepository.findAll() method retrieves all Employee records from the database
        List<Employee> employees = employeeRepository.findAll();

        // Stream through the list of Employee entities, map each Employee to an EmployeeDto,
        // and collect the results into a list
        // 1. Convert the list to a stream: employees.stream()
        // 2. Map each Employee to an EmployeeDto using the EmployeeMapper.mapToEmployeeDto method
        // 3. Collect the mapped EmployeeDto objects into a list using Collectors.toList()
        return employees.stream()
                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }


    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee doesn't exist with given id:"+employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj= employeeRepository.save(employee);
        //basically save performs both save and update
        // if employee doesn't exist then it will create new employee and generate new id for it
        // and if it exists then it will update it

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
        // convert employee to employee dto for consistency in the return type to dto
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee doesn't exist with given id:"+employeeId));

        employeeRepository.deleteById(employeeId);
    }

}
