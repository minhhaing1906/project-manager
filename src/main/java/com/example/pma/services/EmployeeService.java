package com.example.pma.services;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository empRepo;

    @Autowired
    public EmployeeService(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    public Employee save(Employee employee) {
        return empRepo.save(employee);
    }
    public Iterable<Employee> getAll() {
        return empRepo.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return empRepo.findById(id);
    }

    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }

    public void deleteById(Long id) {
        try {
            empRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

    public Employee findByEmail(String email) {
        return empRepo.findByEmail(email);
    }

    public Iterable<Employee> findAll(Pageable pageable) {
        return empRepo.findAll(pageable);
    }
    public void delete(Employee employee) {
        empRepo.delete(employee);
    }
}
