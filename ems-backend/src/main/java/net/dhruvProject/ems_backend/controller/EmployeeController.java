package net.dhruvProject.ems_backend.controller;

import lombok.AllArgsConstructor;
import net.dhruvProject.ems_backend.dto.EmployeeDto;
import net.dhruvProject.ems_backend.entity.Employee;
import net.dhruvProject.ems_backend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//basically in this entire code we are receiving the DTO from the http
// request right then we are passing this to the service layer for business logic

@CrossOrigin("*")
//all the clients are able to call the employee related http
@RestController
// Spring MVC controller and this class is able to handle http requests
@AllArgsConstructor
@RequestMapping("/api/employees")
// we use it to define base url for all apis in this controller

public class EmployeeController {
    //inject dependency
    private EmployeeService employeeService;

    //Build Add Employee Rest API
    @PostMapping
    // this indicates that the http request its responding to is a post request
    //Response Entity converts json to Dto

    //build Add employee Rest api
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        // @requestbody  Indicates that the method parameter employeeDto should be bound to the body of the HTTP request.
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        //Calls the createEmployee method of EmployeeService to create a new employee.
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        //Wraps the result in a ResponseEntity with HTTP status CREATED (201) to indicate that
        //the employee has been successfully created.
    }

    //build Get employee by id Rest api
    @GetMapping("{id}")
    //This annotation maps HTTP GET requests to the getEmployeeById method. The {id} part in the annotation
    // specifies that the method should handle requests to URLs like /1, /2, etc., where 1, 2, etc. are IDs of the employees.
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long EmployeeId)
    // @PathVariable annotation indicates that the method parameter EmployeeId should be bound to the URI template variable id. It means that if
    // a request is made to /1, the value 1 will be passed to the EmployeeId parameter.

    // ResponseEntity<EmployeeDto>: ResponseEntity is a generic type and is used to represent the whole HTTP response: status code, headers
    // and body. Here, it wraps the EmployeeDto object, which will be the body of the response.
    {
        EmployeeDto employeeDto = employeeService.getEmployeeById(EmployeeId);
        // This is a call to the service layer, which contains the business logic.
        //The service layer method getEmployeeById is responsible for fetching the employee details
        //from the database or another data source.

        return ResponseEntity.ok(employeeDto);
        //This returns an HTTP 200 OK status along with the employeeDto object as the response body.
    }

    // build Get all employees by Rest api
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // build update employees with employee id by Rest api
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updatedEmployee(@PathVariable("id") Long employeeId,
                                                       @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto=employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    //build Delete Employee Rest Api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!.");
    }

}
