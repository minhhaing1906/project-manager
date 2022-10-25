package com.example.pma.controllers;

import com.example.pma.dto.ProjectDate;
import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import com.example.pma.services.EmployeeService;
import com.example.pma.services.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService proService;
    private final EmployeeService empService;

    @Autowired
    public ProjectController(ProjectService proService, EmployeeService empService) {
        this.proService = proService;
        this.empService = empService;
    }

    @GetMapping
    public String displayProjectList(Model model) {
        List<Project> projectList = proService.getAll();
        model.addAttribute("projectsList", projectList);
        return "projects/project-list";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model)  {
        Iterable<Employee> all = empService.getAll();

        model.addAttribute("project", new Project());
        model.addAttribute("allEmployees", all);
        return "projects/project-form";
    }

    @PostMapping("/save")
    public String createProject(@ModelAttribute("project") @Valid Project project, Errors errors, Model model) {
        if (errors.hasErrors()) {
            System.out.println("---------------Hit Error Project------------");

            Iterable<Employee> all = empService.getAll();
            model.addAttribute("allEmployees", all);
            return "projects/project-form";
        }

        // handle saving to the database
        proService.save(project);

        // use redirect to prevent duplicate submissions
        return "redirect:/projects";
    }

    @GetMapping("/update/{id}")
    public String updateProject(@PathVariable Long id, Model model) {
        Iterable<Employee> all = empService.getAll();
        Optional<Project> project = proService.findById(id);

        model.addAttribute("project", project);
        model.addAttribute("allEmployees", all);
        return "projects/project-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        Project project = proService.findById(id).get();
        proService.delete(project);
        return "redirect:/projects";
    }

    @GetMapping("/timelines")
    public String getProjectTimelines(Model model) throws JsonProcessingException {
        List<ProjectDate> projectDates = proService.projectDates();

        // convert Java object to JSON for use in JavaScript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTimelineString = objectMapper.writeValueAsString(projectDates);
        System.out.println("-------------project Timelines-------------");
        System.out.println(jsonTimelineString);
        model.addAttribute("projectTimeList", jsonTimelineString);
        return "projects/timelines";
    }
}
