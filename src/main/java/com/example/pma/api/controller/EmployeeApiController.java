package com.example.pma.api.controller;

import com.example.pma.entities.Employee;
import com.example.pma.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
    private final EmployeeService empService;

    @Autowired
    public EmployeeApiController(EmployeeService empService) {
        this.empService = empService;
    }

    @GetMapping
    public Iterable<Employee> getEmployees() {
        return empService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id) {
        return empService.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Valid Employee employee) {
        return empService.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody @Valid Employee employee) {
        return empService.save(employee);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json")
    public Employee partialUpdateEmployee(@RequestBody @Valid Employee patchEmployee, @PathVariable Long id) {
        Employee employee = empService.findById(id).get();

        if (patchEmployee.getFirstName() != null)
            employee.setFirstName(patchEmployee.getFirstName());
        if (patchEmployee.getLastName() != null)
            employee.setLastName(patchEmployee.getLastName());
        if (patchEmployee.getEmail() != null)
            employee.setEmail(patchEmployee.getEmail());

        return empService.save(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        empService.deleteById(id);
    }

    @GetMapping(params = {"page", "size"})
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageAndSize = PageRequest.of(page, size);
        return empService.findAll(pageAndSize);
    }
}
