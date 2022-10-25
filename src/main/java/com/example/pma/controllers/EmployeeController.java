package com.example.pma.controllers;

import com.example.pma.entities.Employee;
import com.example.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService empService;

    @Autowired
    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }

    @GetMapping
    public String displayEmployeeList(Model model) {
        Iterable<Employee> employees = empService.getAll();
        model.addAttribute("employeesList", employees);
        return "employees/employee-list";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String addNewEmployee(@ModelAttribute("employee") @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println("------------Hit error------------");
            return "employees/employee-form";
        }

        empService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/update")
    public String displayEmployeeUpdateForm(@RequestParam Long id, Model model) {
        Optional<Employee> employee = empService.findById(id);

        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam Long id, Model model) {
        Employee employee = empService.findById(id).get();
        empService.delete(employee);
        return "redirect:/employees";
    }

}
