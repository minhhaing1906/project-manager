package com.example.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue (strategy=GenerationType.SEQUENCE, generator="project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
    private long projectId;

    @NotBlank(message = "Must give a project name")
    @Size(min = 2, max = 100)
    private String name;
    @NotNull
    private String stage; // NOT_STARTED, COMPLETED, IN_PROGRESS
    @NotBlank(message = "Must give some description")
    @Size(min = 2, max = 500)
    private String description;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @JsonIgnore
    @NotEmpty(message = "Must select at least an employee")
    private List<Employee> employees;

//    @NotBlank(message = "date can't not be empty")
    @Column(name = "start_date")
    private Date startDate;

//    @NotBlank(message = "date can't not be empty")
    @Column(name = "end_date")
    private Date endDate;

    public Project() {

    }

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    // Convenience method
    public void addEmployee(Employee employee) {
        if (employees == null)
            employees = new ArrayList<>();
        employees.add(employee);
    }
}
