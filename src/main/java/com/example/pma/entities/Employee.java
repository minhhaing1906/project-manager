package com.example.pma.entities;

import com.example.pma.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue (strategy=GenerationType.SEQUENCE, generator="employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    private long employeeId;

    @NotBlank(message = "Must give a first name")
    @Size(min = 2, max = 50, message = "Gotta between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Must give a last name")
    @Size(min = 1, max = 50)
    private String lastName;

    @NotBlank(message = "Must give an email")
    @Email(message = "Must give a valid email")
//    @UniqueValue // custom annotation
    private String email;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @JsonIgnore
    private List<Project> projects;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
