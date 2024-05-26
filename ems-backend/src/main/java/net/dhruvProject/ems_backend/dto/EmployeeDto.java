package net.dhruvProject.ems_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
// here basically we are creating an EmployeeDto class which will only be used
// in interaction and transfer of objects from client to server
// example : api , Api responses
// it will not have any effect on database , it basically abstracts the control
