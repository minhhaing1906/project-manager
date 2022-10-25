package com.example.pma.controllers;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.dto.CountStage;
import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Project;
import com.example.pma.services.EmployeeService;
import com.example.pma.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Value("${version}")
    private String ver;
    private final ProjectService proService;
    private final EmployeeService empService;

    @Autowired
    public HomeController(ProjectService proService, EmployeeService empService) {
        this.proService = proService;
        this.empService = empService;
    }

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
        model.addAttribute("versionNumber", ver);

        List<Project> projects = proService.getAll();
        model.addAttribute("projectsList", projects);

        List<EmployeeProject> employeeProjectsCount = empService.employeeProjects();
        model.addAttribute("employeeProjectsCount", employeeProjectsCount);

        // convert Java object to JSON for use in JavaScript
        List<CountStage> countStages = proService.getProjectStatus();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(countStages);
        model.addAttribute("projectStatusCnt", jsonString);
        return "main/home";
    }

}
