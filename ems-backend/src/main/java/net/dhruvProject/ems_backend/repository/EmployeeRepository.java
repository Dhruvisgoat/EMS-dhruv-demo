package net.dhruvProject.ems_backend.repository;

import net.dhruvProject.ems_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
//    when the employee repository extends the jpa repository then it gets
//    the ability to perform crud operations on the employee
}

// In summary, using an interface for the repository in Spring Data JPA provides several advantages:

//1.It abstracts the data access layer and provides a clear contract for data operations.
//2.Spring Data JPA automatically generates the implementation, reducing boilerplate code.
//3.It supports convention-over-configuration for defining query methods.
//4.It offers flexibility for custom queries and implementations.
//5.It integrates seamlessly with Spring's features like dependency injection, paging,
// sorting, and advanced querying capabilities