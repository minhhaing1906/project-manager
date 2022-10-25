package com.example.pma.validators;

import com.example.pma.entities.Employee;
import com.example.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {
    private final EmployeeService empService;

    @Autowired
    public UniqueValidator(EmployeeService empService) {
        this.empService = empService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Entered validation method");

        Employee employee = empService.findByEmail(s);
        return employee == null;
    }
}
