package com.example.pma.dao;

import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RepositoryRestResource(collectionResourceRel = "api-employees", path = "api-employees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    // custom query
    @Query(nativeQuery = true,
            value = "SELECT e.employee_id AS employeeId, e.first_name AS firstName, e.last_name AS lastName, e.email AS email, COUNT(pe.project_id) AS projectCount " +
                    "FROM employee as e " +
                    "LEFT JOIN project_employee as pe " +
                    "ON e.employee_id = pe.employee_id " +
                    "GROUP BY e.employee_id")
    List<EmployeeProject> employeeProjects();

    Employee findByEmail(String email);
}
