package net.dhruvProject.ems_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
//    In JPA (Java Persistence API) with Hibernate as the ORM provider, the default naming strategy is to convert camelCase field
//    names to snake_case column names in the database. This means that if you have a field named lastName in your
//    Employee entity, Hibernate will automatically map it to a column named last_name in the database.
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email_id" ,nullable=false,unique=true)
    private String email;
}